/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.HashSet;
import java.util.Set;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Topsy
 */
@Stateless
@LocalBean
public class UserIdGenerator implements NumberGenerator {

     String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";

    final java.util.Random rand = new java.util.Random();

    final Set<String> identifiers = new HashSet<>();

    @Override
    public String generateNumber() {
        StringBuilder builder = new StringBuilder();
        while (builder.toString().length() == 0) {
            int length = rand.nextInt(5) + 5;
            for (int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            if (identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        String generatedId = "UID"+builder.toString();
        return generatedId;
    }
}
