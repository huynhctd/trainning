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
            if((code.matches("(?=^(\\D*\\d\\D*){7}$)(?:([0-9]{1,3}(-[0-9]{1,3}){2,6}))")) == false) {
                throw new IllegalArgumentException("Code format fail");
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
