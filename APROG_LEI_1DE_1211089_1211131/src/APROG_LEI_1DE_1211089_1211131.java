import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class APROG_LEI_1DE_1211089_1211131 {
    static final File FICHEIRO = new File("Matriz.txt");

    public static void main(String[] args) throws FileNotFoundException {
        int[][] mapaDoTerreno = lerDimencoesTerreno();
        exercicioB(mapaDoTerreno);
        int[][] novoMapaDoTerreno = novoArray(mapaDoTerreno);
        exercicioC(novoMapaDoTerreno);
        exercicioD(novoMapaDoTerreno);
        exercicioE(mapaDoTerreno, novoMapaDoTerreno);
        exercicioF(novoMapaDoTerreno);
        exercicioG(novoMapaDoTerreno);
        exercicioH(mapaDoTerreno, novoMapaDoTerreno);
        int[][] copiaNovoMapaDoTerreno = novoArray(mapaDoTerreno);
        exercicioI(copiaNovoMapaDoTerreno);
        exercicioJ(copiaNovoMapaDoTerreno);
    }

    public static int[][] lerDimencoesTerreno() throws FileNotFoundException {
        Scanner sc = new Scanner(FICHEIRO);
        sc.nextLine();
        int linhas = sc.nextInt();
        int colunas = sc.nextInt();
        int[][] dimencoesTerreno = new int[linhas][colunas];
        for (int linha = 0; linha < dimencoesTerreno.length; linha++) {
            for (int coluna = 0; coluna < dimencoesTerreno[0].length; coluna++) {
                dimencoesTerreno[linha][coluna] = sc.nextInt();
            }
        }
        sc.close();
        return dimencoesTerreno;
    }

    public static void mostrarResultado(int[][] mapaDoTerreno) {
        for (int i = 0; i < mapaDoTerreno.length; i++) {
            for (int j = 0; j < mapaDoTerreno[0].length; j++) {
                System.out.printf("%3d", mapaDoTerreno[i][j]);
            }
            System.out.println();
        }
    }

    public static void exercicioB(int[][] mapaDoTerreno) {
        System.out.println("b)");
        mostrarResultado(mapaDoTerreno);
    }

    public static void exercicioC(int[][] novoMapaDoTerreno) {
        System.out.println("c)");
        mostrarResultado(novoMapaDoTerreno);
    }

    public static int[][] novoArray(int[][] mapaDoTerreno) {
        int[][] nova = new int[mapaDoTerreno.length][];
        for (int i = 0; i < mapaDoTerreno.length; i++) {
            nova[i] = new int[mapaDoTerreno[i].length];
            for (int j = 0; j < mapaDoTerreno[0].length; j++) {
                nova[i][j] = mapaDoTerreno[i][j] + 1;
            }
        }
        return nova;
    }

    public static void exercicioD(int[][] mapaDoTerreno) {
        System.out.println("d)");
        double percentagem = calcularAreaInundada(mapaDoTerreno);
        System.out.printf("area submersa: %.2f%%\n", percentagem);
    }

    private static double calcularAreaInundada(int[][] mapaDoTerreno) {
        int quantidadeBaixoAgua = 0;
        int total = 0;
        for (int i = 0; i < mapaDoTerreno.length; i++) {
            for (int j = 0; j < mapaDoTerreno[0].length; j++) {
                if (mapaDoTerreno[i][j] < 0) {
                    quantidadeBaixoAgua++;
                }
                total++;
            }
        }
        double percentagem = ((double) quantidadeBaixoAgua / total) * 100;
        return percentagem;
    }

    public static void exercicioE(int[][] mapaDoTerreno, int[][] novoMapaDoTerreno) {
        System.out.println("e)");
        int valor = (int) (calcularAreaMetrosQuadrados(novoMapaDoTerreno) - calcularAreaMetrosQuadrados(mapaDoTerreno));
        System.out.println("variacao da area inundada: " + valor + " m2");

    }

    private static int calcularAreaMetrosQuadrados(int[][] matrizDoTerreno) {
        int quantidadeBaixoAgua = 0;
        for (int i = 0; i < matrizDoTerreno.length; i++) {
            for (int j = 0; j < matrizDoTerreno[0].length; j++) {
                if (matrizDoTerreno[i][j] < 0) {
                    quantidadeBaixoAgua++;
                }
            }
        }
        return quantidadeBaixoAgua;
    }

    public static void exercicioF(int[][] mapaDoTerreno) {
        System.out.println("f)");
        int soma = 0;
        for (int i = 0; i < mapaDoTerreno.length; i++) {
            for (int j = 0; j < mapaDoTerreno[0].length; j++) {
                if (mapaDoTerreno[i][j] < 0) {
                    soma += mapaDoTerreno[i][j];
                }
            }
        }
        System.out.println("volume de agua: " + -soma + " m3");
    }

    public static int inundacaoTotal(int[][] terrenoParaTestar) {
        int maiorAltura = 0;
        for (int i = 0; i < terrenoParaTestar.length; i++) {
            for (int j = 0; j < terrenoParaTestar[0].length; j++) {
                if (maiorAltura < terrenoParaTestar[i][j]) {
                    maiorAltura = terrenoParaTestar[i][j];
                }
            }
        }
        if (maiorAltura == 0) {
            return 0;
        }
        return maiorAltura + 1;
    }

    public static void exercicioG(int novoMapaDoTerreno[][]) {
        System.out.println("g) \npara inundacao total, subir : " + inundacaoTotal(novoMapaDoTerreno) + " m");

    }

    public static void exercicioH(int[][] mapaDoTerreno, int[][] novoMapaDoTerreno) {
        System.out.println("h)");
        System.out.println("subida da agua (m) | area inundada (m2)");
        System.out.println("------------------ | ------------------");
        int nivelDaAgua = inundacaoTotal(mapaDoTerreno);
        if (nivelDaAgua == 0) {
            System.out.println("Terreno todo submerso");
        } else {
            for (int i = 1; i <= nivelDaAgua + 1; i++) {
                subtrairUm(novoMapaDoTerreno);
                System.out.printf("%18d |%19d%n", i, parcelasBaixoDaAgua(novoMapaDoTerreno));
            }
        }
    }

    public static int parcelasBaixoDaAgua(int[][] novoMapaDoTerreno) {
        int numeroDeParcelas = 0;
        for (int i = 0; i < novoMapaDoTerreno[0].length; i++) {
            for (int j = 0; j < novoMapaDoTerreno.length; j++) {
                if (novoMapaDoTerreno[j][i] == -1) {
                    numeroDeParcelas++;
                }
            }
        }
        return numeroDeParcelas;
    }

    public static void subtrairUm(int[][] mapadoTerreno) {
        for (int i = 0; i < mapadoTerreno.length; i++) {
            for (int j = 0; j < mapadoTerreno[0].length; j++) {
                mapadoTerreno[i][j] = mapadoTerreno[i][j] - 1;
            }
        }
    }

    public static void exercicioI(int[][] copiaNovoMapaDoTerreno) {
        System.out.println("i)");
        int terraDeslocada = 0;
        int coordenadaNorte = 0;
        int coordenadaOeste = 0;
        boolean guardarCoordenadas = true;
        int terraMenor = 1000;
        int GuardarCoordenadaNorte = 1000;
        int GuardarCoordenadaOeste = 1000;

        for (int percorrerLinhas = 0; percorrerLinhas < copiaNovoMapaDoTerreno.length - 2; percorrerLinhas++) {
            for (int percorrerColunas = 0; percorrerColunas < copiaNovoMapaDoTerreno[0].length - 2; percorrerColunas++) {
                for (int i = percorrerLinhas; i < percorrerLinhas + 3; i++) {
                    for (int j = percorrerColunas; j < percorrerColunas + 3; j++) {
                        int cotaDoTerreno = copiaNovoMapaDoTerreno[i][j];
                        if (guardarCoordenadas == true) {
                            coordenadaNorte = i;
                            coordenadaOeste = j;
                            guardarCoordenadas = false;
                        }
                        terraDeslocada = getTerraDeslocada(terraDeslocada, cotaDoTerreno);
                    }
                }
                guardarCoordenadas = true;
                if (terraMenor > terraDeslocada) {
                    terraMenor = terraDeslocada;
                    GuardarCoordenadaNorte = coordenadaNorte;
                    GuardarCoordenadaOeste = coordenadaOeste;
                } else if (terraMenor == terraDeslocada) {
                    if (GuardarCoordenadaNorte > coordenadaNorte) {
                        GuardarCoordenadaNorte = coordenadaNorte;
                        GuardarCoordenadaOeste = coordenadaOeste;
                    } else if (GuardarCoordenadaNorte == coordenadaNorte && GuardarCoordenadaOeste > coordenadaOeste) {
                        GuardarCoordenadaNorte = coordenadaNorte;
                        GuardarCoordenadaOeste = coordenadaOeste;
                    }
                }
                terraDeslocada = 0;
            }
        }
        System.out.println("coordenadas do cubo: (" + GuardarCoordenadaNorte + "," + GuardarCoordenadaOeste + "), terra a mobilizar: " + terraMenor + " m2");
    }

    public static int getTerraDeslocada(int terraDeslocada, int cotaDoTerreno) {
        if (cotaDoTerreno > -3) {
            while (cotaDoTerreno > -3) {
                cotaDoTerreno = cotaDoTerreno - 1;
                terraDeslocada = terraDeslocada + 1;
            }
        }else if (cotaDoTerreno < -3) {
            while (cotaDoTerreno < -3) {
                cotaDoTerreno = cotaDoTerreno + 1;
                terraDeslocada = terraDeslocada + 1;
            }
        }
        return terraDeslocada;
    }

    public static void exercicioJ(int[][] copiaNovoMapaDoTerreno) {
        System.out.println("j)");
        int terrenoSeco = 0, coluna = 0, guardarColuna = -1;
        for (int percorrerColuna = 0; percorrerColuna < copiaNovoMapaDoTerreno[0].length; percorrerColuna++) {
            for (int i = 0; i < copiaNovoMapaDoTerreno.length; i++) {
                for (int j = percorrerColuna; j < percorrerColuna + 1; j++) {
                    if (copiaNovoMapaDoTerreno[i][j] >= 0) {
                        terrenoSeco = terrenoSeco + 1;
                        coluna = j;
                    }
                }
            }
            if (terrenoSeco == copiaNovoMapaDoTerreno.length) {
                if (guardarColuna < coluna) {
                    guardarColuna = coluna;
                }
            }
            terrenoSeco = 0;
        }
        if (guardarColuna != -1) {
            System.out.println("caminho seco na vertical na coluna (" + guardarColuna + ")");
        } else {
            System.out.println("não há caminho seco na vertical");
        }
    }
}