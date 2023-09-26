public class AlgebraBooleana {
    public static void main(String[] args) {
        boolean notFim;
        int quantNumeros;
        String entradaString = new String();
        int tam;
        char[] entradaChar = new char[1000];

        do {
            quantNumeros = MyIO.readInt();
            notFim = notFim(quantNumeros);
            if (notFim) {
                String[] listaValores = lerValores(quantNumeros);
                entradaString = MyIO.readLine();
                str2char(entradaString, entradaChar);
                tam = entradaString.length();
                tam = removerEspacos(entradaChar, tam);
                trocar(entradaChar, listaValores, tam);
                char resp = algebraBooleana(entradaChar, tam);
                MyIO.println(resp);
            }
        } while (notFim);
    }

    public static void str2char(String entradaString, char[] entradaChar) {
        for (int i = 0; i < entradaString.length(); i++) {
            entradaChar[i] = entradaString.charAt(i);
        }
    }

    public static int removerEspacos(char[] entradaChar, int tam) {
        for (int i = 0; i < tam; i++) {
            if (entradaChar[i] == ' ' || entradaChar[i] == ',') {
                for (int j = i; j < tam - 1; j++) {
                    entradaChar[j] = entradaChar[j + 1];
                }
                i--;
                entradaChar[tam] = '\0';
                tam--;
            }
        }
        return tam;
    }

    public static void trocar(char[] entradaChar, String[] listaValores, int tam) {
        int index = 0;
        for (int i = 0; i < listaValores.length; i++) {
            for (int j = 0; j < tam; j++) {
                if ((int) entradaChar[j] == index + 65) {
                    entradaChar[j] = (char) listaValores[index].charAt(0);
                }
            }
            index++;
        }
    }

    public static String[] lerValores(int quantValores) {
        String[] listaValores = new String[quantValores];
        for (int i = 0; i < quantValores; i++) {
            listaValores[i] = MyIO.readString();
        }
        return listaValores;
    }

    public static char not(char[] valores, int quantValores) {
        char retorno = '1';
        for (int i = 0; i < quantValores; i++) {
            if (valores[i] == '1') {
                retorno = '0';
                i = quantValores;
                valores = new char[10];
            }
        }
        return retorno;
    }

    public static char and(char[] valores, int quantValores) {
        char retorno = '1';
        for (int i = 0; i < quantValores; i++) {
            if (valores[i] == '0') {
                retorno = '0';
                i = quantValores;
                valores = new char[10];
            }
        }
        return retorno;
    }

    public static char or(char[] valores, int quantValores) {
        char retorno = '0';
        for (int i = 0; i < quantValores; i++) {
            if (valores[i] == '1') {
                retorno = '1';
                i = quantValores;
                valores = new char[10];
            }
        }
        return retorno;
    }

    public static char algebraBooleana(char[] entradaChar, int tam) {
        char substituir;
        char[] valores = new char[10];
        int quantValores = 0;

        for (int i = 0; i < tam; i++) {
            if (entradaChar[i] == ')') {
                for (int j = i; j > 0; j--) {
                    if (entradaChar[j] == '(') {
                        if (entradaChar[j - 1] == 't') {
                            valores[quantValores] = entradaChar[j + 1];
                            quantValores++;
                            substituir = not(valores, quantValores);
                            entradaChar[j - 3] = substituir;
                            for (int k = 0; k < quantValores + 4; k++) {
                                for (int w = j - 2; w < tam - 1; w++) {
                                    entradaChar[w] = entradaChar[w + 1];
                                }
                                entradaChar[tam - k] = '\0';
                            }
                            tam -= quantValores + 4;
                            quantValores = 0;
                            i = 0;
                            j = 0;
                        } else if (entradaChar[j - 1] == 'd') {
                            for (int k = j + 1; k < i; k++) {
                                valores[quantValores] = entradaChar[k];
                                quantValores++;
                            }
                            substituir = and(valores, quantValores);
                            entradaChar[j - 3] = substituir;
                            for (int k = 0; k < quantValores + 4; k++) {
                                for (int w = j - 2; w < tam - 1; w++) {
                                    entradaChar[w] = entradaChar[w + 1];
                                }
                                entradaChar[tam - k] = '\0';
                            }
                            tam -= quantValores + 4;
                            quantValores = 0;
                            i = 0;
                            j = 0;
                        } else if (entradaChar[j - 1] == 'r') {
                            for (int k = j + 1; k < i; k++) {
                                valores[quantValores] = entradaChar[k];
                                quantValores++;
                            }
                            substituir = or(valores, quantValores);
                            entradaChar[j - 2] = substituir;
                            for (int k = 0; k < quantValores + 3; k++) {
                                for (int w = j - 1; w < tam - 1; w++) {
                                    entradaChar[w] = entradaChar[w + 1];
                                }
                                entradaChar[tam - k] = '\0';
                            }
                            tam -= quantValores + 3;
                            quantValores = 0;
                            i = 0;
                            j = 0;
                        }
                    }
                }
            }
        }
        return entradaChar[0];
    }

    public static void alterar(String entradaString) {
        return;
    }

    public static boolean notFim(int quantNumeros) {
        boolean notFim = true;
        if (quantNumeros == 0) {
            notFim = false;
        }
        return notFim;
    }
}
