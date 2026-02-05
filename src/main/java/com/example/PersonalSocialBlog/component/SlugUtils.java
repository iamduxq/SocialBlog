package com.example.PersonalSocialBlog.component;

import java.text.Normalizer;
import java.util.Locale;

public class SlugUtils {
    public static String toSlug(String input) {
        if (input == null) return null;
        String slug = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{InCOMBINING_DIACRITICAL_MARKS}+", "")
                .toLowerCase(Locale.ROOT)
                .replaceAll("[^a-z0-0]+","-")
                .replaceAll("^-|-$","");
        return slug;
    }
}
