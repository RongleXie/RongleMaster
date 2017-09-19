package com.ronglexie.licenseclient;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;

/**
 * 参数校验
 *
 * @author wxt.linx
 * @version 2017-7-12.
 */
public class LicenseCheck {

    /**
     * 参数检查
     *
     * @param exParam
     * @throws de.schlichtherle.license.LicenseContentException
     */
    public static void check(Map<String, String> exParam) throws de.schlichtherle.license.LicenseContentException {
        String ip = exParam.get("ip");
        String mac = exParam.get("mac");
        try {
            if (!validatoIpAndMacAddress(ip, mac)) {
                throw new de.schlichtherle.license.LicenseContentException("invalid.ipmac");
            }
        }catch (SocketException e){
            throw new de.schlichtherle.license.LicenseContentException("err.ipmac");
        }
    }

    /**
     * 校验Mac
     *
     * @param macAddress
     * @return
     * @throws SocketException
     */
    public static boolean validateMacAddress(String macAddress) throws SocketException {
        boolean returnFlag = false;
        Enumeration<NetworkInterface> nets = NetworkInterface
                .getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets)) {
            byte[] mac = netint.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            if (mac != null) {
                for (int i = 0; i < mac.length; i++) {
                    sb.append(String.format("%02X%s", mac[i],
                            (i < mac.length - 1) ? "-" : ""));
                }
            }
            if (sb.toString().equals(macAddress)) {
                returnFlag = true;
            }
        }
        return returnFlag;

    }

    /**
     * 检验ip和Mac
     *
     * @param ipAddress
     * @param macAddress
     * @return
     * @throws SocketException
     */
    public static boolean validatoIpAndMacAddress(String ipAddress, String macAddress) throws SocketException {
        boolean returnFlag = false;
        Enumeration<NetworkInterface> nets = NetworkInterface
                .getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets)) {
            byte[] mac = netint.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            if (mac != null) {
                for (int i = 0; i < mac.length; i++) {
                    sb.append(String.format("%02X%s", mac[i],
                            (i < mac.length - 1) ? "-" : ""));
                }
            }
            if (sb.toString().equals(macAddress)) {
                Enumeration<InetAddress> inetAddresses = netint
                        .getInetAddresses();
                String ip = "";
                for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                    ip = inetAddress.getHostAddress();
                    if (ipAddress.toString().equals(ip)) {
                        returnFlag = true;
                    }
                }
            }
        }
        return returnFlag;
    }
}
