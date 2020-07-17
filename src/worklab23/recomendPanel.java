package worklab23;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class recomendPanel extends javax.swing.JPanel {

    /*
    Kullaniciya oneri yapmak ve onerileri tabloda gostermek icin olusturulmus sinif.
     */
    private int oneriler[]; //Oneri yapilacak turlerin tutuldugu dizi.
    ArrayList<Integer> ids = new ArrayList<>(); //Kullaniciya onerilecek film idlerini tutan dizi.
    ArrayList<Integer> types = new ArrayList<>(); //Kullaniciya onerilecek film turlerini tutan dizi.
    ArrayList<Float> points = new ArrayList<>(); //Kullaniciya onerilecek filmlerin puanlarini tutan dizi.

    public recomendPanel(int[] oneriId) {
        initComponents();
        this.oneriler = oneriId; //Bu turler kayit panelinden buraya gonderilir.
        createBest(); //oneri yapilacak turler arasindaki en iyi filmleri secen method.
        createTable(); //Oneri yapilacak turleri tabloya bastiran method.
    }

    private class dataClass implements Comparable<dataClass> {

        /*
        Kullaniciya sunulacak filmerin bilgilerini tutmak icin olusturulmus 
        tek kullanimlik class.
         */
        int id; //film idsi
        int pointsum; //bu filmi izleyen herkesin verdigi puanlarin toplamini tutar.
        int number; //filmin izlenme sayisi.
        int typeid; //filmin turu.
        float point; //filmin ortalama puani.

        public dataClass(int id, int pointsum, int number, int type) {
            //constructor.
            this.id = id;
            this.pointsum = pointsum;
            this.number = number;
            this.typeid = type;
            point = 0;
        }

        public dataClass(int id, int point) {
            this.id = id;
            this.point = point;
        }

        /*
        Filmleri siralamak icin olusturulmus method. 
        Collections.sort methodunun calismasi icin lazim.
         */
        @Override
        public int compareTo(dataClass o) {
            float compdata;
            compdata = ((dataClass) o).point;

            //ascending order
            return Float.compare(compdata, this.point);

            //descending order
            //return compareQuantity - this.quantity;
        }
    }

    private void createTable() { //en iyi filmlerin tabloda gosterilmesi icin bir method.
        dbConnect msql = new dbConnect(); //veritabani baglantisi.
        Connection con = msql.getConnection();
        Object dataList[][]; //Tabloda gosterilecek verileri tutacak matris.
        ArrayList<Object[]> temp = new ArrayList<>(); //verilerin gecici olarak tutulacakgi arraylist.
        try {
            Statement stmt = con.createStatement();
            for (int i = 0; i < ids.size(); i++) {//6 adet filmin bilgilerinin dongu icinde cekilmesi.
                String sorgu = String.format("Select * from program p, programtype pt,type t where p.idprogram ='%d' and pt.idprogram = p.idprogram and pt.idtype = '%d' and t.idtype='%d'  ", ids.get(i), types.get(i), types.get(i));
                //6 adet filmin her birisinin isim ve diger bilgilerinin film idsi ve turlerine gore sorgulanmasi.
                ResultSet rs = stmt.executeQuery(sorgu); //sorgunun calistirilmasi.
                Object[] arr = new Object[3]; //film bilgilerinin tutulacagi gecici dizi.
                System.out.println("---------");
                while (rs.next()) {
                    System.out.println(rs.getString(2));
                    System.out.println(points.get(i));
                    System.out.println(rs.getString(10));
                    arr[0] = rs.getString(2); //film adinin eklenmesi.
                    arr[1] = rs.getString(10); //film turunun adinin eklenmesi.
                    arr[2] = points.get(i); //filmin ortalama puaninin eklenmesi.
                    temp.add(arr); //gecici arrayliste bu film bilgilerinin kayit edilmesi.
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        dataList = new Object[temp.size()][]; //Tabloda gosterilecek bilgilerin tutulacagi 
        //arraylistin olusturulmasi.
        for (int i = 0; i < temp.size(); i++) {
            dataList[i] = temp.get(i);//gecici arraylissteki bilgilerin matrise atilmasi.
        }
        table.setModel(new javax.swing.table.DefaultTableModel( //verilerin tabloya eklenmesi.
                dataList, //Verilerin tablo icine gonderilmesi.
                new String[]{
                    "Ad", "Tür", "Puan" //Tablodaki basliklarin belirlenmesi.
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false //Tablodaki verilerin degismesinin engenllenmesi.
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(searchPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createBest() {
        dbConnect msql = new dbConnect(); //Veritabani baglantisi.
        Connection con = msql.getConnection();
        ArrayList<dataClass> programid = new ArrayList<>(); //Onerilecek 6 adet filmin tutuldugu liste.
        try {
            Statement stmt = con.createStatement();
            /*
            Bir dongu icerisinde oneri yapilacak turlerdeki filmler sirasiyla seciliyor.
            Bu filmlerden puan verilmis olanlarin puanlari toplaniyor ve toplam izlenme sayisina bolunuyor.
            Daha sonra cekilen filmler puanina gore siralaniyor ve en iyi 2'si olmak uzere
            6 adet film bastiriliyor.
             */
            for (int i = 0; i < oneriler.length; i++) { //Turlerin sirasiyla gezilmesi.
                programid.clear(); //Her tur basladiginda siralama yapilacak arraylist sifirlaniyor.
                //Boylece farkli turlerden filmler ayni dizide bulunmuyor.
                String sorgu = String.format("Select * from userprogram up,programtype pt where pt.idtype = '%d' and pt.idprogram=up.programid", oneriler[i]);
                System.out.println("String: " + sorgu);
                ResultSet rs = stmt.executeQuery(sorgu); //Sorgununu calimasi.
                //secilen turde izlenen ve puanlanan filmerin veritabanindan cekilmesi.
                while (rs.next()) { //Secilen turdeki her film sirayla geziliyor.
                    boolean check = false; //Cekilen filmin daha once arrayliste eklenip eklenmedigini kontrol eder.
                    int x = 0; //Eger film daha once eklendiyse hangi indexte oldugunu tutar.
                    for (int j = 0; j < programid.size(); j++) {
                        if (programid.get(j).id == rs.getInt(3)) {
                            check = true; //Cekilen film daha once listeye eklenmisse dongu kiriliyor.
                            x = j; //Film daha once ekliyse o indexi esitler.
                            break;
                        }
                    }
                    if (check == false) { //Film daha once eklenmediyse.
                        programid.add(new dataClass(rs.getInt(3), rs.getInt(7), 1, oneriler[i]));
                        //filmin id'si, puani, izlenme sayisi ve tur id'si arrayliste eklenir.
                    } else {//Eger daha once eklendiyse.
                        programid.get(x).number++; //izlenme sayisi 1 arttirilir.
                        programid.get(x).pointsum += rs.getInt(7); //Toplam puani eklenir.
                    }
                }
                for (int j = 0; j < programid.size(); j++) {
                    programid.get(j).point = programid.get(j).pointsum / programid.get(j).number;
                    //Tum filmler eklendikten sonra filmlerin ortalma puanlari toplam puanin izlenme sayisina bolunmesiyle bulunur.
                }

                Collections.sort(programid); //SEcilen turdeki En iyi filmler siralaniyor.
                if (programid.size() == 1) { //eger secilen turde sadece 1 film varsa buraya girer.
                    ids.add(programid.get(0).id); //1. oneri ekleniyor.
                    points.add(programid.get(0).point); //1. onerinin puani ekleniyor.
                    types.add(programid.get(0).typeid); //1. onerinin turu ekleniyor.
                } else if (programid.size() > 1) { //1den fazla varsa
                    ids.add(programid.get(0).id); //1. oneri ekleniyor.
                    points.add(programid.get(0).point); //1. onerinin puani eklenir.
                    types.add(programid.get(0).typeid); //1. onerinin turu eklenir..
                    ids.add(programid.get(1).id);  //2.oneri ekleniyor.
                    points.add(programid.get(1).point);//2. onerinin puani eklenir.
                    types.add(programid.get(1).typeid); //2. onerinin turu eklenir.
                }
            }

            System.out.println("Size:" + ids.size());

        } catch (SQLException e) {
            System.out.println(e);
        }

        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(searchPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Ad", "Tür", "Puan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        jLabel1.setText("Kaydınız Başarıyla Tamamlanmıştır");

        jLabel2.setText("Seçtiğiniz türlere göre bazı önerilerimiz:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
