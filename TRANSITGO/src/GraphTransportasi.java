public class GraphTransportasi {
    String namaModa;
    GraphNode[][] adjacencyList;
    int gridSize;
    
    GraphTransportasi(String nama, int size) {
        this.namaModa = nama;
        this.gridSize = size;
        this.adjacencyList = new GraphNode[size][size];
    }
    
    void tambahKoneksi(int dariBaris, int dariKolom, int keBaris, int keKolom, int waktu, int harga) {
        GraphNode baru = new GraphNode(keBaris, keKolom, waktu, harga);
        baru.next = adjacencyList[dariBaris][dariKolom];
        adjacencyList[dariBaris][dariKolom] = baru;
    }
    
    void tambahKoneksiDuaArah(int baris1, int kolom1, int baris2, int kolom2, int waktu, int harga) {
        tambahKoneksi(baris1, kolom1, baris2, kolom2, waktu, harga);
        tambahKoneksi(baris2, kolom2, baris1, kolom1, waktu, harga);
    }
    
    boolean adaKoneksi(int baris, int kolom) {
        if (baris < 0 || baris >= gridSize || kolom < 0 || kolom >= gridSize) {
            return false;
        }
        return adjacencyList[baris][kolom] != null;
    }
    
    GraphNode getKoneksi(int baris, int kolom) {
        if (baris < 0 || baris >= gridSize || kolom < 0 || kolom >= gridSize) {
            return null;
        }
        return adjacencyList[baris][kolom];
    }
    
    int hitungKoneksi(int baris, int kolom) {
        int count = 0;
        GraphNode current = adjacencyList[baris][kolom];
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
    
    void hapusSemuaKoneksi(int baris, int kolom) {
        adjacencyList[baris][kolom] = null;
    }
    
    boolean adaKoneksiLangsung(int dariBaris, int dariKolom, int keBaris, int keKolom) {
        GraphNode current = adjacencyList[dariBaris][dariKolom];
        while (current != null) {
            if (current.zonaBaris == keBaris && current.zonaKolom == keKolom) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    GraphNode getKoneksiLangsung(int dariBaris, int dariKolom, int keBaris, int keKolom) {
        GraphNode current = adjacencyList[dariBaris][dariKolom];
        while (current != null) {
            if (current.zonaBaris == keBaris && current.zonaKolom == keKolom) {
                return current;
            }
            current = current.next;
        }
        return null;
    }
}
