import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class NparaN {
    private static final String host = "localhost";
    private static final String usrName = "root";
    private static final String password = "12345678";
    private static final String database = "trabalhoaibm";

    public static int nbyn(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver não disponível");
        }
        String line = "";
        Connection c = null;
        try{
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/trabalhoaibm?useTimezone=true&serverTimezone=UTC", usrName, password);
        } catch (Exception e){
            System.out.println(e);
        }

        ////////////////////////////////////////dim_desct_exames_has_dim_n_exames////////////////////////
        int stopper =1;

        try{
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("urgency_exams.csv"));
            int linha = 1;
            while ((line = br.readLine()) != null){   //returns a Boolean value
                if(stopper == 1){
                    stopper++;
                }
                else{
                    int tab = 1;
                    String[] cells = line.split(";"); // use comma as separator
                    Statement stm;
                    int i = 1;
                    String s = null;

                    //Inserir Tabela dim_categoria - 1
                    try {
                        if (cells[2].contains("'") && cells[1].contains("'"))
                            s = "INSERT IGNORE INTO dim_desct_exames_has_dim_n_exames (id_desc_exam, id_n_exames) VALUES" +
                                    "((select id_desc_exam from dim_desct_exames where descricao = \""+ cells[2]+"\")," +
                                    "(select id_n_exames from dim_n_exames where id_n_exames = \""+ cells[1]+"\"))";
                        else if (!cells[2].contains("'") && cells[1].contains("'"))
                            s = "INSERT IGNORE INTO dim_desct_exames_has_dim_n_exames (id_desc_exam, id_n_exames) VALUES" +
                                    "((select id_desc_exam from dim_desct_exames where descricao = '"+ cells[2]+"')," +
                                    "(select id_n_exames from dim_n_exames where id_n_exames = \""+ cells[1]+"\"))";
                        else if(cells[2].contains("'") && !cells[1].contains("'"))
                            s = "INSERT IGNORE INTO dim_desct_exames_has_dim_n_exames (id_desc_exam, id_n_exames) VALUES" +
                                    "((select id_desc_exam from dim_desct_exames where descricao = \""+ cells[2]+"\")," +
                                    "(select id_n_exames from dim_n_exames where id_n_exames = '"+ cells[1]+"'))";
                        else
                            s = "INSERT IGNORE INTO dim_desct_exames_has_dim_n_exames (id_desc_exam, id_n_exames) VALUES" +
                                    "((select id_desc_exam from dim_desct_exames where descricao = '"+ cells[2]+"')," +
                                    "(select id_n_exames from dim_n_exames where id_n_exames = '"+ cells[1]+"'))";
                        stm = c.createStatement();
                        i = stm.executeUpdate(s);
                    } catch (SQLException ex) {
                        if(!ex.toString().contains("Duplicate entry")){
                            System.out.println(ex);
                            System.out.println("Linha: " + linha);
                        }
                    }
                    linha++;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    //////////////////////////////////dim_prescricao_has_facts_episodio///////////////////////
        try{
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("urgency_prescriptions.csv"));
            int linha = 1;
            while ((line = br.readLine()) != null){   //returns a Boolean value
                if(stopper == 1){
                    stopper++;
                }
                else{
                    int tab = 1;
                    String[] cells = line.split(";"); // use comma as separator
                    Statement stm;
                    int i = 1;
                    String s = null;

                    //Inserir Tabela dim_categoria - 1
                    try {
                        if (cells[6].contains("'"))
                            s = "INSERT IGNORE INTO dim_prescricao_has_facts_episodio (id_prescricao, urg_episodio) VALUES" +
                                    "((select id_prescricao from dim_prescricao p where id_prof_prescreve = "+ Integer.parseInt(cells[2])+" and id_data = (select id_data from dim_data where data = '"+cells[3]+"' ))," +
                                    "(select urg_episodio from facts_episodio where urg_episodio = "+ Integer.parseInt(cells[0])+"))";
                        else
                            s = "INSERT IGNORE INTO dim_prescricao_has_facts_episodio (id_prescricao, urg_episodio) VALUES" +
                                    "((select id_prescricao from dim_prescricao p where id_prof_prescreve = "+ Integer.parseInt(cells[2])+" and id_data = (select id_data from dim_data where data = '"+cells[3]+"' ))," +
                                    "(select urg_episodio from facts_episodio where urg_episodio = "+ Integer.parseInt(cells[0])+"))";


                        stm = c.createStatement();
                        i = stm.executeUpdate(s);
                    } catch (SQLException ex) {
                        if(!ex.toString().contains("Duplicate entry")){
                            System.out.println(ex);
                            System.out.println("Linha: " + linha);
                        }
                    }
                    linha++;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        //////////////////////////////////////////////dim_desc_exames_has_facts_episodio/////////////////////////////////

        try{
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("urgency_exams.csv"));
            int linha = 1;
            while ((line = br.readLine()) != null){   //returns a Boolean value
                if(stopper == 1){
                    stopper++;
                }
                else{
                    int tab = 1;
                    String[] cells = line.split(";"); // use comma as separator
                    Statement stm;
                    int i = 1;
                    String s = null;

                    //Inserir Tabela dim_categoria - 1
                    try {
                        if (cells[2].contains("'"))
                            s = "INSERT IGNORE INTO dim_desct_exames_has_facts_episodio (id_desc_exam, urg_episodio) VALUES" +
                                    "((select id_desc_exam from dim_desct_exames where descricao = \""+ cells[2]+"\")," +
                                    "(select urg_episodio from facts_episodio where urg_episodio = "+ Integer.parseInt(cells[0])+"))";
                        else
                            s = "INSERT IGNORE INTO dim_desct_exames_has_facts_episodio (id_desc_exam, urg_episodio) VALUES" +
                                    "((select id_desc_exam from dim_desct_exames where descricao = '"+ cells[2]+"')," +
                                    "(select urg_episodio from facts_episodio where urg_episodio = "+ Integer.parseInt(cells[0])+"))";


                        stm = c.createStatement();
                        i = stm.executeUpdate(s);
                    } catch (SQLException ex) {
                        if(!ex.toString().contains("Duplicate entry")){
                            System.out.println(ex);
                            System.out.println("Linha: " + linha);
                        }
                    }
                    linha++;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        //////////////////////////////////////////////dim_prescricao_has_dim_medicamento/////////////////////////////////

        try{
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("urgency_prescriptions.csv"));
            int linha = 1;
            while ((line = br.readLine()) != null){   //returns a Boolean value
                if(stopper == 1){
                    stopper++;
                }
                else{
                    int tab = 1;
                    String[] cells = line.split(";"); // use comma as separator
                    Statement stm;
                    int i = 1;
                    String s = null;

                    //Inserir Tabela dim_categoria - 1
                    try {
                        if (cells[6].contains("'") && cells[7].contains("'"))
                            s = "INSERT IGNORE INTO dim_prescricao_has_dim_medicamento (id_prescricao, id_medicamento,dosagem,quantidade) VALUES" +
                                    "((select id_prescricao from dim_prescricao p where id_prof_prescreve = "+ Integer.parseInt(cells[2])+" and id_data = (select id_data from dim_data where data = '"+cells[3]+"' ))," +
                                    "(select id_medicamento from dim_medicamento where descricao = \""+ cells[7]+"\"), \""+cells[6]+"\"," + Integer.parseInt(cells[5])+")";
                        else if(!cells[6].contains("'") && cells[7].contains("'"))
                            s = "INSERT IGNORE INTO dim_prescricao_has_dim_medicamento (id_prescricao, id_medicamento,dosagem,quantidade) VALUES" +
                                    "((select id_prescricao from dim_prescricao p where id_prof_prescreve = "+ Integer.parseInt(cells[2])+" and id_data = (select id_data from dim_data where data = '"+cells[3]+"' ))," +
                                    "(select id_medicamento from dim_medicamento where descricao = \""+ cells[7]+"\"), '"+cells[6]+"'," + Integer.parseInt(cells[5])+")";
                        else if(cells[6].contains("'") && !cells[7].contains("'"))
                            s = "INSERT IGNORE INTO dim_prescricao_has_dim_medicamento (id_prescricao, id_medicamento,dosagem,quantidade) VALUES" +
                                    "((select id_prescricao from dim_prescricao p where id_prof_prescreve = "+ Integer.parseInt(cells[2])+" and id_data = (select id_data from dim_data where data = '"+cells[3]+"' ))," +
                                    "(select id_medicamento from dim_medicamento where descricao = '"+ cells[7]+"'), \""+cells[6]+"\"," + Integer.parseInt(cells[5])+")";
                        else
                            s = "INSERT IGNORE INTO dim_prescricao_has_dim_medicamento (id_prescricao, id_medicamento,dosagem,quantidade) VALUES" +
                                    "((select id_prescricao from dim_prescricao p where id_prof_prescreve = "+ Integer.parseInt(cells[2])+" and id_data = (select id_data from dim_data where data = '"+cells[3]+"' ))," +
                                    "(select id_medicamento from dim_medicamento where descricao = '"+ cells[7]+"'), '"+cells[6]+"'," + Integer.parseInt(cells[5])+")";
                        stm = c.createStatement();
                        i = stm.executeUpdate(s);
                    } catch (SQLException ex) {
                        if(!ex.toString().contains("Duplicate entry")){
                            System.out.println(ex);
                            System.out.println("Linha: " + linha);
                        }
                    }
                    linha++;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        try{
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("urgency_procedures.csv"));
            int linha = 1;
            while ((line = br.readLine()) != null){   //returns a Boolean value
                if(stopper == 1){
                    stopper++;
                }
                else{
                    int tab = 1;
                    String[] cells = line.split(";"); // use comma as separator
                    Statement stm;
                    int i = 1;
                    String s = null;
                    int count = 0;
                    //Inserir Tabela dim_categoria - 1
                    try {
                        if (cells[5].contains("'") && cells[8].contains("'"))
                            s = "INSERT IGNORE INTO facts_episodio_has_dim_procedimento (urg_episodio, id_procedimento) VALUES" +
                                    "((select urg_episodio from facts_episodio where urg_episodio = "+ Integer.parseInt(cells[0])+")," +
                                    "(select distinct id_procedimento from dim_procedimento where especificacao = \""+ cells[5]+"\" and cancelamento = "+ Integer.parseInt(cells[6])+" " +
                                    "and id_data = (select id_data from dim_data where data = '"+cells[4]+"')" +
                                    "and id_intervencao = (select id_intervencao from dim_intervencao where descricao = \""+cells[8]+"\")" +
                                    "and id_presc_procedimento = (select id_presc_procedimento from dim_presc_procedimento  where id_presc_procedimento = " + Integer.parseInt(cells[3]) +" "+
                                    "and id_profissional = "+Integer.parseInt(cells[1])+" and" +
                                    " id_data = (select id_data from dim_data where data = '"+ cells[2]+"'))))";
                        else if (!cells[5].contains("'") && cells[8].contains("'"))
                            s = "INSERT IGNORE INTO facts_episodio_has_dim_procedimento (urg_episodio, id_procedimento) VALUES" +
                                    "((select urg_episodio from facts_episodio where urg_episodio = "+ Integer.parseInt(cells[0])+")," +
                                    "(select distinct id_procedimento from dim_procedimento where especificacao = '"+ cells[5]+"' and cancelamento = "+ Integer.parseInt(cells[6])+" " +
                                    "and id_data = (select id_data from dim_data where data = '"+cells[4]+"')" +
                                    "and id_intervencao = (select id_intervencao from dim_intervencao where descricao = \""+cells[8]+"\")" +
                                    "and id_presc_procedimento = (select id_presc_procedimento from dim_presc_procedimento where id_presc_procedimento = " + Integer.parseInt(cells[3]) +" and  id_profissional = "+Integer.parseInt(cells[1])+" and" +
                                    " id_data = (select id_data from dim_data where data = '"+ cells[2]+"'))))";
                        else if (cells[5].contains("'") && !cells[8].contains("'"))
                            s = "INSERT IGNORE INTO facts_episodio_has_dim_procedimento (urg_episodio, id_procedimento) VALUES" +
                                    "((select urg_episodio from facts_episodio where urg_episodio = "+ Integer.parseInt(cells[0])+")," +
                                    "(select distinct id_procedimento from dim_procedimento where especificacao = \""+ cells[5]+"\" and cancelamento = "+ Integer.parseInt(cells[6])+" " +
                                    "and id_data = (select id_data from dim_data where data = '"+cells[4]+"')" +
                                    "and id_intervencao = (select id_intervencao from dim_intervencao where descricao = '"+cells[8]+"')" +
                                    "and id_presc_procedimento = (select id_presc_procedimento from dim_presc_procedimento  where id_presc_procedimento = " + Integer.parseInt(cells[3]) +" and id_profissional = "+Integer.parseInt(cells[1])+" and" +
                                    " id_data = (select id_data from dim_data where data = '"+ cells[2]+"'))))";
                        else
                            s = "INSERT IGNORE INTO facts_episodio_has_dim_procedimento (urg_episodio, id_procedimento) VALUES" +
                                    "((select urg_episodio from facts_episodio where urg_episodio = "+ Integer.parseInt(cells[0])+")," +
                                    "(select distinct id_procedimento from dim_procedimento where especificacao = '"+ cells[5]+"' and cancelamento = "+ Integer.parseInt(cells[6])+" " +
                                    "and id_data = (select id_data from dim_data where data = '"+cells[4]+"')" +
                                    "and id_intervencao = (select id_intervencao from dim_intervencao where descricao = '"+cells[8]+"')" +
                                    "and id_presc_procedimento = (select id_presc_procedimento from dim_presc_procedimento where  id_presc_procedimento = " + Integer.parseInt(cells[3]) + " and id_profissional = "+Integer.parseInt(cells[1])+" and" +
                                    " id_data = (select id_data from dim_data where data = '"+ cells[2]+"'))))";

                        stm = c.createStatement();
                        i = stm.executeUpdate(s);
                        if (stm.getWarnings()!= null && !stm.getWarnings().getMessage().contains("Duplicate entry")){
                            System.out.println(stm.getWarnings());
                            System.out.println(linha);
                            count++;
                        }

                    } catch (SQLException ex) {
                        if(!ex.toString().contains("Duplicate entry")){
                            System.out.println(ex);
                            System.out.println("Linha: " + linha);
                        }
                    }
                    linha++;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }


        return 0;
    }
    public static void main(String[] args){
       // int res = run();
        int nbyn = nbyn();
    }

}
