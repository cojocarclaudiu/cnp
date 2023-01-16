import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

public class CNP {

    private int length = 13;
    private String cnpCode;
    private String sexCode;
    private LocalDate date;
    private String country;
    private int controller;

    public CNP(int firstDigit, String cnpCode) {
        this.cnpCode = cnpCode;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        System.out.println(cnpCode.substring(1, 7));
        if (sexHashMap.containsKey(firstDigit)) {
            this.sexCode = sexHashMap.get(firstDigit);
            if (isValid(cnpCode.substring(1, 7))) {
                this.date = LocalDate.parse(cnpCode.substring(1, 7), DateTimeFormatter.ofPattern("yyMMdd"));
                if (countyMap.containsKey(Integer.parseInt(cnpCode.substring(7, 9)))) {
                    this.country = countyMap.get(Integer.parseInt(cnpCode.substring(7, 9)));
                    if (controlKey(cnpCode)) {
                        System.out.println("The CNP " + this.cnpCode + " is valid . Sex:" + this.sexCode + " " +
                                ", Date of birth:" + this.date + ", the country:" + this.country);
                    } else System.err.println("The control key is not valid");
                } else System.err.println("The country code is not valid...");
            } else System.err.println("The date is not valid...");
        } else System.err.println("First digit is not valid...");
    }

    private boolean controlKey(String cnpCode) {
        Long cnp = Long.parseLong(String.valueOf(cnpCode));
        Long controlNumber = 279146358279L;
        long finall = 0;

        long controll = cnp % 10;
        cnp /= 10;

        while (cnp > 0) {
            finall += (cnp % 10) * (controlNumber % 10);
            cnp /= 10;
            controlNumber /= 10;
        }
        if (finall % 11 == 10) {
            finall = 1;
        }
        System.out.println(finall % 11 + "  ==  " + controll);
        if (finall % 11 == controll) return true;
        else return false;
    }

    private Map<Integer, String> sexHashMap = Map.of(
            1, "man (1900-1999)",
            2, "woman (1900-1999)",
            3, "man (1800-1899)",
            4, "woman (1800-1899)",
            5, "man (2000-2099)",
            6, "woman (2000-2099)",
            7, "man (resident)",
            8, "woman (resident)"
    );

    private Map<Integer, String> countyMap = Map.ofEntries(
            Map.entry(1, "Alba"),
            Map.entry(2, "Arad"),
            Map.entry(3, "Argeș"),
            Map.entry(4, "Bacău"),
            Map.entry(5, "Bihor"),
            Map.entry(6, "Bistrița-Năsăud"),
            Map.entry(7, "	Botoșani"),
            Map.entry(8, "	Brașov"),
            Map.entry(9, "	Brăila"),
            Map.entry(10, "Buzău"),
            Map.entry(11, "Caraș-Severin"),
            Map.entry(12, "Cluj"),
            Map.entry(13, "Constanța"),
            Map.entry(14, "Covasna"),
            Map.entry(15, "Dâmbovița"),
            Map.entry(16, "Dolj"),
            Map.entry(17, "Galați"),
            Map.entry(18, "Gorj"),
            Map.entry(19, "Harghita"),
            Map.entry(20, "Hunedoara"),
            Map.entry(21, "Ialomița"),
            Map.entry(22, "Iași"),
            Map.entry(23, "Ilfov"),
            Map.entry(24, "Maramureș"),
            Map.entry(25, "Mehedinți"),
            Map.entry(26, "Mureș"),
            Map.entry(27, "Neamț"),
            Map.entry(28, "Olt"),
            Map.entry(29, "Prahova"),
            Map.entry(30, "Satu Mare"),
            Map.entry(31, "Sălaj"),
            Map.entry(32, "Sibiu"),
            Map.entry(33, "Suceava"),
            Map.entry(34, "Teleorman"),
            Map.entry(35, "Timiș"),
            Map.entry(36, "Tulcea"),
            Map.entry(37, "Vaslui"),
            Map.entry(38, "Vâlcea"),
            Map.entry(39, "Vrancea"),
            Map.entry(40, "București"),
            Map.entry(41, "București - Sector 1"),
            Map.entry(42, "București - Sector 2"),
            Map.entry(43, "București - Sector 3"),
            Map.entry(44, "București - Sector 4"),
            Map.entry(45, "București - Sector 5"),
            Map.entry(46, "București - Sector 6"),
            Map.entry(51, "Călărași"),
            Map.entry(52, "Giurgiu"),
            Map.entry(47, "Bucuresti - Sector 7 (desfiintat)"),
            Map.entry(48, "Bucuresti - Sector 8 (desfiintat)")
    );

    public boolean isValid(String dateStr) {
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyMMdd"));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
