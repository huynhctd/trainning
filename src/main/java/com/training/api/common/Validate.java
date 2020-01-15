package com.training.api.common;

public class Validate {
    /**
     * Address utils
     */
    public static class AddressValidates {
        /**
         * Check number code
         * @param code code
         */
        public static void checkHalfSize(String code) {
            if(!code.matches("\\d+")) {
                throw new IllegalArgumentException("Code must be half size number");
            }
        }

        /**
         * Check number code
         * @param code code
         */
        public static void checkFormat (String code) {
            if((code.matches("(?=^(\\D*\\d\\D*){7}$)(^(\\d{1,3}(-\\d{1,3}){2,6})$)")) == false) {
                throw new IllegalArgumentException("Code must be include numbers of no more than 3 consecutive numbers separated by \"-\" Ex: xxx-xx-x-x\"");
            }
        }

        /**
         * Convert code
         * @param code code
         * @return code
         */
        public static String replaceCode(String code) {
            return code.replaceAll(" ", "").replaceAll("-", "");
        }
    }
}
