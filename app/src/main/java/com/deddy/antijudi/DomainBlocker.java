package com.deddy.antijudi;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class DomainBlocker {

    private final Set<String> blockedDomains = new HashSet<>();

    public void load(Context context) throws IOException {
        blockedDomains.clear();

        InputStream input = context.getAssets()
                .open("blocked_domains.txt");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(input, "UTF-8")
        );

        String line;

        while ((line = reader.readLine()) != null) {
            String domain = line.trim().toLowerCase(Locale.ROOT);

            if (!domain.isEmpty() && !domain.startsWith("#")) {
                while (domain.endsWith(".")) {
                    domain = domain.substring(0, domain.length() - 1);
                }

                blockedDomains.add(domain);
            }
        }

        reader.close();
    }

    public boolean isBlocked(String domain) {
        if (domain == null) {
            return false;
        }

        domain = domain.trim().toLowerCase(Locale.ROOT);

        while (domain.endsWith(".")) {
            domain = domain.substring(0, domain.length() - 1);
        }

        if (blockedDomains.contains(domain)) {
            return true;
        }

        for (String blocked : blockedDomains) {
            if (domain.endsWith("." + blocked)) {
                return true;
            }
        }

        return false;
    }
}
