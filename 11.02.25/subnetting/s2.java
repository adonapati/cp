package subnetting;

/*
Write a program that takes an IP address and subnet mask (in CIDR notation, 
e.g., 192.168.1.1/24) as input and calculates the network and broadcast addresses.

Input Format:
---------------
A String, IPAddress
An integer, CIDR

Output Format:
---------------
Space separated IP addresses, network IP and broadcast IP.


Sample Input-1:
-----------------
192.168.1.10
24

Sample Output-1:
------------------
192.168.1.0 192.168.1.255


Sample Input-2:
-----------------
192.0.2.1
24

Sample Output-2:
------------------
192.0.2.0 192.0.2.255

*/

import java.util.*;
import java.net.*;
public class s2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ipa = sc.next(); 
        int cidr = sc.nextInt(); 
        try {
            InetAddress ip = InetAddress.getByName(ipa);
            byte[] bytes = ip.getAddress();
            int mask = 0xFFFFFFFF << (32 - cidr);
            byte[] mb = {
                (byte) (mask >>> 24),
                (byte) (mask >>> 16),
                (byte) (mask >>> 8),
                (byte) mask
            };
            byte[] nw = new byte[4];
            byte[] bdc = new byte[4];
            for (int i = 0; i < 4; i++) {
                nw[i] = (byte) (bytes[i] & mb[i]);
                bdc[i] = (byte) (nw[i] | ~mb[i]);
            }
            InetAddress nwa = InetAddress.getByAddress(nw);
            InetAddress bdca = InetAddress.getByAddress(bdc);
            System.out.println(nwa.getHostAddress() + " " + bdca.getHostAddress());
        } catch (UnknownHostException e) {
            System.out.println("Invalid IP Address");
        }
        sc.close();
    }
}