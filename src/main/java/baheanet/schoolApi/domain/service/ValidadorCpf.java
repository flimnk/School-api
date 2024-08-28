package baheanet.schoolApi.domain.service;

import baheanet.schoolApi.infra.excepitions.ValidaRegraDeNegocios;

public class ValidadorCpf {

    public static boolean isValidCpf(String cpf) {
        // Remove todos os caracteres não numéricos
        String cleanedCpf = cpf.replaceAll("\\D", "");

        // Verifica se o CPF contém 11 dígitos
        if (cleanedCpf.length() != 11) {
            throw new ValidaRegraDeNegocios("O CPF deve conter 11 dígitos.");
        }

        // Verifica se todos os dígitos são iguais (CPF inválido)
        if (cleanedCpf.chars().allMatch(ch -> ch == cleanedCpf.charAt(0))) {
            throw new ValidaRegraDeNegocios("O CPF não pode ter todos os dígitos iguais.");
        }

        // Verifica o dígito verificador
        if (!isCpfValid(cleanedCpf)) {
            throw new ValidaRegraDeNegocios("O CPF é inválido de acordo com os dígitos verificadores.");
        }

        return true; // CPF é válido
    }

    private static boolean isCpfValid(String cpf) {
        int[] digits = cpf.chars().map(c -> c - '0').toArray();

        // Calcula o primeiro dígito verificador
        int firstVerifier = calculateVerifierDigit(digits, 10);
        if (firstVerifier != digits[9]) {
            return false;
        }

        // Calcula o segundo dígito verificador
        int secondVerifier = calculateVerifierDigit(digits, 11);
        return secondVerifier == digits[10];
    }

    private static int calculateVerifierDigit(int[] digits, int weight) {
        int sum = 0;
        for (int i = 0; i < weight - 1; i++) {
            sum += digits[i] * (weight - i);
        }
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }


}
