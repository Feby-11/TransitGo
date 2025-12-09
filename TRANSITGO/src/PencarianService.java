public class PencarianService {
    
    public static int linearSearchByNama(Zona[][] kotaGrid, int gridSize, String keyword, 
                                      StackRiwayat riwayat) {
        return linearSearchTempat(kotaGrid, gridSize, keyword, true, riwayat);
    }

    public static int linearSearchByKategori(Zona[][] kotaGrid, int gridSize, String keyword,
                                              StackRiwayat riwayat) {
        return linearSearchTempat(kotaGrid, gridSize, keyword, false, riwayat);
    }

    private static int linearSearchTempat(Zona[][] kotaGrid, int gridSize, String keyword,
                                           boolean byNama, StackRiwayat riwayat) {
        int ditemukan = 0;
        String keywordLower = keyword.toLowerCase();
        
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                Zona zona = kotaGrid[i][j];
                TempatNode current = zona.headTempat;
                
                while (current != null) {
                    boolean cocok;
                    
                    if (byNama) {
                        cocok = current.nama.toLowerCase().contains(keywordLower);
                    } else {
                        cocok = current.kategori.toLowerCase().contains(keywordLower);
                    }
                    
                    if (cocok) {
                        ditemukan++;
                        TampilanUI.tampilkanItemHasil(ditemukan, current.nama, 
                                                      current.kategori, i, j, zona.namaZona);
                        riwayat.push(keyword, i, j, current.nama);
                    }
                    
                    current = current.next;
                }
            }
        }
        
        return ditemukan;
    }

    public static TempatNode linearSearchExact(Zona zona, String namaTempat) {
        TempatNode current = zona.headTempat;
        
        while (current != null) {
            if (current.nama.equalsIgnoreCase(namaTempat)) {
                return current;
            }
            current = current.next;
        }
        
        return null;
    }

    public static Zona linearSearchZonaByNama(Zona[][] kotaGrid, int gridSize, String namaZona) {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (kotaGrid[i][j].namaZona.equalsIgnoreCase(namaZona)) {
                    return kotaGrid[i][j];
                }
            }
        }
        return null;
    }

    public static int linearSearchRuteByModa(HasilRute[] hasilRute, int jumlahHasil, String moda) {
        for (int i = 0; i < jumlahHasil; i++) {
            if (hasilRute[i].moda.equalsIgnoreCase(moda)) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearchRuteByWaktu(HasilRute[] hasilRute, int jumlahHasil, int targetWaktu) {
        int left = 0;
        int right = jumlahHasil - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (hasilRute[mid].totalWaktu == targetWaktu) {
                return mid;
            } else if (hasilRute[mid].totalWaktu < targetWaktu) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }
    
    public static int binarySearchRuteByHarga(HasilRute[] hasilRute, int jumlahHasil, int targetHarga) {
        int left = 0;
        int right = jumlahHasil - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (hasilRute[mid].totalHarga == targetHarga) {
                return mid;
            } else if (hasilRute[mid].totalHarga < targetHarga) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }

    public static int binarySearchLowerBound(HasilRute[] hasilRute, int jumlahHasil, int targetWaktu) {
        int left = 0;
        int right = jumlahHasil;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (hasilRute[mid].totalWaktu < targetWaktu) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    

    public static int jumpSearchRuteByWaktu(HasilRute[] hasilRute, int jumlahHasil, int targetWaktu) {
        if (jumlahHasil == 0) return -1;
        
        int jump = (int) Math.sqrt(jumlahHasil);
        int prev = 0;
        int curr = jump;
        
        while (curr < jumlahHasil && hasilRute[curr].totalWaktu < targetWaktu) {
            prev = curr;
            curr += jump;
        }
        
        for (int i = prev; i < Math.min(curr + 1, jumlahHasil); i++) {
            if (hasilRute[i].totalWaktu == targetWaktu) {
                return i;
            }
        }
        
        return -1;
    }

    public static int interpolationSearchRuteByWaktu(HasilRute[] hasilRute, int jumlahHasil, int targetWaktu) {
        int low = 0;
        int high = jumlahHasil - 1;
        
        while (low <= high && targetWaktu >= hasilRute[low].totalWaktu 
               && targetWaktu <= hasilRute[high].totalWaktu) {
            

            if (hasilRute[high].totalWaktu == hasilRute[low].totalWaktu) {
                if (hasilRute[low].totalWaktu == targetWaktu) {
                    return low;
                }
                return -1;
            }

            int pos = low + ((targetWaktu - hasilRute[low].totalWaktu) * (high - low)) 
                      / (hasilRute[high].totalWaktu - hasilRute[low].totalWaktu);
            
            if (hasilRute[pos].totalWaktu == targetWaktu) {
                return pos;
            } else if (hasilRute[pos].totalWaktu < targetWaktu) {
                low = pos + 1;
            } else {
                high = pos - 1;
            }
        }
        
        return -1;
    }
}