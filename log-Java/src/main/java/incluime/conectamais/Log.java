package incluime.conectamais;

public class Log {
    void saidaLog(){

                System.out.println("[05/03/2026 09:12:21] INFO");
                System.out.println("Usuário: Maria Silva");
                System.out.println("Perfil: Familiar");
                System.out.println("Ação: Login realizado");
                System.out.println("Status: Sucesso");
                System.out.println("IP: 192.168.0.15");
                System.out.println();

                System.out.println("[05/03/2026 09:14:02] AVISO");
                System.out.println("Usuário: maria.silva@email.com");
                System.out.println("Perfil: Familiar");
                System.out.println("Ação: Tentativa de login");
                System.out.println("Status: Senha incorreta");
                System.out.println("IP: 192.168.0.15");
                System.out.println();

                System.out.println("[05/03/2026 10:01:33] INFO");
                System.out.println("Usuário: Carlos Mendes");
                System.out.println("Perfil: Diretor");
                System.out.println("Ação: Login realizado");
                System.out.println("Status: Sucesso");
                System.out.println("IP: 192.168.0.05");
                System.out.println();

                System.out.println("[05/03/2026 10:05:12] ALERT");
                System.out.println("Usuário: carlos.mendes@empresa.com");
                System.out.println("Perfil: Diretor");
                System.out.println("Ação: Tentativa de login");
                System.out.println("Status: 3 tentativas de senha incorreta");
                System.out.println("IP: 192.168.0.05");
    }
}
