/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.net.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * TrustManager factory for generating custom TrustManagers.
 * 
 * @since 3.0
 */
public final class TrustManagerFactory
{
    private static final X509Certificate[] EMPTY_X509CERTIFICATE_ARRAY = new X509Certificate[]{};

    private static class TrustManager implements X509TrustManager {

        private final boolean checkServerValidity;
        
        TrustManager(boolean checkServerValidity) {
            this.checkServerValidity = checkServerValidity;
        }
        /**
         * Never generates a CertificateException.
         */
        public void checkClientTrusted(X509Certificate[] certificates, String authType)
        {
            return;
        }

        public void checkServerTrusted(X509Certificate[] certificates, String authType) throws CertificateException
        {
            if (checkServerValidity) {
                for (int i = 0; i < certificates.length; ++i)
                {
                    certificates[i].checkValidity();
                }
            }
        }

        /**
         * @return an empty array of certificates
         */
        public X509Certificate[] getAcceptedIssuers()
        {
            return EMPTY_X509CERTIFICATE_ARRAY;
        }        
    }

    private static final X509TrustManager ACCEPT_ALL=new TrustManager(false);
    
    private static final X509TrustManager CHECK_SERVER_VALIDITY=new TrustManager(true);

    /**
     * Generate a TrustManager that performs no checks.
     * 
     * @return the TrustManager
     */
    public static X509TrustManager getAcceptAllTrustManager(){
        return ACCEPT_ALL;
    }

    /**
     * Generate a TrustManager that checks server certificates for validity,
     * but otherwise performs no checks.
     * 
     * @return the validating TrustManager
     */
    public static X509TrustManager getValidateServerCertificateTrustManager(){
        return CHECK_SERVER_VALIDITY;        
    }

}