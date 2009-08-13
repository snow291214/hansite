/* jcifs smb client library in Java
 * Copyright (C) 2002  "Michael B. Allen" <jcifs at samba dot org>
 *                   "Jason Pugsley" <jcifs at samba dot org>
 *                   "skeetz" <jcifs at samba dot org>
 *                   "Eric Glass" <jcifs at samba dot org>
 *                   and Marcel, Thomas, ...
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package ru.sgnhp;

import java.io.*;
import java.security.Principal;
import javax.servlet.*;
import javax.servlet.http.*;
import jcifs.*;
import jcifs.http.NtlmSsp;
import jcifs.smb.SmbSession;
import jcifs.smb.NtlmChallenge;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbAuthException;
import jcifs.util.Base64;
import jcifs.util.LogStream;

/**
 * This servlet Filter can be used to negotiate password hashes with
 * MSIE clients using NTLM SSP. This is similar to <tt>Authentication:
 * BASIC</tt> but weakly encrypted and without requiring the user to re-supply
 * authentication credentials.
 * <p>
 * Read <a href="../../../ntlmhttpauth.html">jCIFS NTLM HTTP Authentication and the Network Explorer Servlet</a> for complete details.
 */
public class NtlmHttpFilter extends jcifs.http.NtlmHttpFilter {

    private static LogStream log = LogStream.getInstance();
    private String defaultDomain = "snos.ru";
    private String domainController = "lyra.snos.ru";
    private boolean loadBalance;
    private boolean enableBasic;
    private boolean insecureBasic;
    private String realm;

    @Override
    public void destroy() {
    }

    /**
     * This method simply calls <tt>negotiate( req, resp, false )</tt>
     * and then <tt>chain.doFilter</tt>. You can override and call
     * negotiate manually to achive a variety of different behavior.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        NtlmPasswordAuthentication ntlm = null;

        if ((req.getRemoteAddr().startsWith("192"))) {
            log.println("Address: " + req.getRemoteAddr());
            Config.setProperty("jcifs.smb.lmCompatibility", "0");
            Config.setProperty("jcifs.smb.client.useExtendedSecurity", "false");
            Config.setProperty("jcifs.http.domainController", "sigma.dosnos.local");
            Config.setProperty("jcifs.netbios.wins", "192.168.2.40");
            Config.setProperty("jcifs.smb.client.domain", "DOSNOS");
        } else {
            Config.setProperty("jcifs.smb.lmCompatibility", "0");
            Config.setProperty("jcifs.smb.client.useExtendedSecurity", "false");
            Config.setProperty("jcifs.http.domainController", "lyra.snos.ru");
            Config.setProperty("jcifs.netbios.wins", "10.1.32.1");
            Config.setProperty("jcifs.smb.client.domain", "ASU");
        }

        if ((ntlm = negotiate(req, resp, false)) == null) {
            if ((ntlm = negotiate(req, resp, false)) == null) {
                return;
            }
        }

        chain.doFilter(new NtlmHttpServletRequest(req, ntlm), response);
    }

    /**
     * Negotiate password hashes with MSIE clients using NTLM SSP
     * @param req The servlet request
     * @param resp The servlet response
     * @param skipAuthentication If true the negotiation is only done if it is
     * initiated by the client (MSIE post requests after successful NTLM SSP
     * authentication). If false and the user has not been authenticated yet
     * the client will be forced to send an authentication (server sends
     * HttpServletResponse.SC_UNAUTHORIZED).
     * @return True if the negotiation is complete, otherwise false
     */
    @Override
    protected NtlmPasswordAuthentication negotiate(HttpServletRequest req,
            HttpServletResponse resp,
            boolean skipAuthentication) throws IOException, ServletException {
        UniAddress dc;
        String msg;
        NtlmPasswordAuthentication ntlm = null;
        msg = req.getHeader("Authorization");
        boolean offerBasic = enableBasic && (insecureBasic || req.isSecure());
        Config.setProperty("jcifs.smb.lmCompatibility", "0");
        Config.setProperty("jcifs.smb.client.useExtendedSecurity", "false");

        if (msg != null && (msg.startsWith("NTLM ") ||
                (offerBasic && msg.startsWith("Basic ")))) {
            if (msg.startsWith("NTLM ")) {
                HttpSession ssn = req.getSession();
                byte[] challenge;

                if (loadBalance) {
                    NtlmChallenge chal = (NtlmChallenge) ssn.getAttribute("NtlmHttpChal");
                    if (chal == null) {
                        chal = SmbSession.getChallengeForDomain();
                        ssn.setAttribute("NtlmHttpChal", chal);
                    }
                    dc = chal.dc;
                    challenge = chal.challenge;
                } else {
                    dc = UniAddress.getByName(domainController, true);
                    challenge = SmbSession.getChallenge(dc);
                }

                if ((ntlm = NtlmSsp.authenticate(req, resp, challenge)) == null) {
                    return null;
                }
                /* negotiation complete, remove the challenge object */
                ssn.removeAttribute("NtlmHttpChal");
            } else {
                String auth = new String(Base64.decode(msg.substring(6)),
                        "US-ASCII");
                int index = auth.indexOf(':');
                String user = (index != -1) ? auth.substring(0, index) : auth;
                String password = (index != -1) ? auth.substring(index + 1) : "";
                index = user.indexOf('\\');
                if (index == -1) {
                    index = user.indexOf('/');
                }
                String domain = (index != -1) ? user.substring(0, index) : defaultDomain;
                user = (index != -1) ? user.substring(index + 1) : user;
                ntlm = new NtlmPasswordAuthentication(domain, user, password);
                dc = UniAddress.getByName(domainController, true);
            }
            try {

                SmbSession.logon(dc, ntlm);

                if (log.level > 2) {
                    log.println("NtlmHttpFilter: " + ntlm +
                            " successfully authenticated against " + dc);
                }
            } catch (SmbAuthException sae) {
                if (log.level > 1) {
                    log.println("NtlmHttpFilter: " + ntlm.getName() +
                            ": 0x" + jcifs.util.Hexdump.toHexString(sae.getNtStatus(), 8) +
                            ": " + sae);
                }
                if (sae.getNtStatus() == sae.NT_STATUS_ACCESS_VIOLATION) {
                    /* Server challenge no longer valid for
                     * externally supplied password hashes.
                     */
                    HttpSession ssn = req.getSession(false);
                    if (ssn != null) {
                        ssn.removeAttribute("NtlmHttpAuth");
                    }
                }
                resp.setHeader("WWW-Authenticate", "NTLM");
                if (offerBasic) {
                    resp.addHeader("WWW-Authenticate", "Basic realm=\"" +
                            realm + "\"");
                }
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                resp.setContentLength(0); /* Marcel Feb-15-2005 */
                resp.flushBuffer();
                return null;
            }
            req.getSession().setAttribute("NtlmHttpAuth", ntlm);
        } else {
            if (!skipAuthentication) {
                HttpSession ssn = req.getSession(false);
                if (ssn == null || (ntlm = (NtlmPasswordAuthentication) ssn.getAttribute("NtlmHttpAuth")) == null) {
                    resp.setHeader("WWW-Authenticate", "NTLM");
                    if (offerBasic) {
                        resp.addHeader("WWW-Authenticate", "Basic realm=\"" +
                                realm + "\"");
                    }
                    resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    resp.setContentLength(0);
                    resp.flushBuffer();
                    return null;
                }
            }
        }

        return ntlm;
    }
}

class NtlmHttpServletRequest extends HttpServletRequestWrapper {

    Principal principal;

    NtlmHttpServletRequest(HttpServletRequest req, Principal principal) {
        super(req);
        this.principal = principal;
    }

    public String getRemoteUser() {
        return principal.getName();
    }

    public Principal getUserPrincipal() {
        return principal;
    }

    public String getAuthType() {
        return "NTLM";
    }
}
