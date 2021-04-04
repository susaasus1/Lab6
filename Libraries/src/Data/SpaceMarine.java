package Data;

import Exceptions.FiledIncorrect;
import Exceptions.WrongSpaceMarineException;
import Reader.ConsoleReader;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SpaceMarine implements Comparable<SpaceMarine>,Serializable {
    private static final long serialVersionUID = -6565259818539791441L;
    private Long id;
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDate creationDate;
    private Float health;
    private double height;
    private AstartesCategory category;
    private Weapon weaponType;
    private Chapter chapter;

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public SpaceMarine(Long id, String name, Coordinates coordinates, LocalDate creationDate, Float health, double height, AstartesCategory category, Weapon weaponType, Chapter chapter) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.health = health;
        this.height = height;
        this.category = category;
        this.weaponType = weaponType;
        this.chapter = chapter;
    }

    public SpaceMarine() {
    }

    public static SpaceMarine parseSpaceMarine(String xml) throws FiledIncorrect, WrongSpaceMarineException {
        SpaceMarine spaceMarine = new SpaceMarine(1L,"s",new Coordinates(2,3),LocalDate.now(),1f,1,AstartesCategory.CHAPLAIN,Weapon.HEAVY_FLAMER,new Chapter("s","s",1,"s"));
        try {
           String tmp = null;
           Long id;
           Float health = 0F;
           double height = 0;
           float x = 0;
           String xml1=xml;

           int y = 0, year = 0, month = 0, dayOfMonth = 0;
           Integer marinesCount = 0;
           Pattern pattern = Pattern.compile(">.+<");
           String name = null, nameChapter = null, parentLegion = null, world = null;
           if (xml1.contains("<id>")) {

               Matcher matcher = pattern.matcher(xml1);
               if (matcher.find()) {
                   tmp = xml1.substring(matcher.start() + 1, matcher.end() - 1);
                   xml1=xml1.replace("<id>","");
                   xml1=xml1.replace("</id>", "");
               }

               id = Long.parseLong(tmp);
               spaceMarine.setId(id);
           }

           if ((xml1.contains("<name>"))) {
               Matcher matcher = pattern.matcher(xml1);

               if (matcher.find()) {
                   tmp = xml1.substring(matcher.start() + 1, matcher.end() - 1);
                   xml1=xml1.replace("<name>"," ");
                   xml1=xml1.replace("</name>", " ");
               }
               name = tmp;
               spaceMarine.setName(name);
           }

           if (xml1.contains("<x>")) {
               Matcher matcher = pattern.matcher(xml1);
               if (matcher.find()) {
                   tmp = xml1.substring(matcher.start() + 1, matcher.end() - 1);
                   xml1=xml1.replace("<x>"," ");
                   xml1=xml1.replace("</x>", " ");
               }
               x = Float.parseFloat(tmp);
           }
           if (xml1.contains("<y>")) {
               Matcher matcher = pattern.matcher(xml1);
               if (matcher.find()) {
                   tmp = xml1.substring(matcher.start() + 1, matcher.end() - 1);
                   xml1=xml1.replace("<y>"," ");
                   xml1=xml1.replace("</y>", " ");
               }
               y = Integer.parseInt(tmp);
           }
           spaceMarine.setCoordinates(new Coordinates(x, y));
           if (xml1.contains("<year>")) {
               Matcher matcher = pattern.matcher(xml1);
               if (matcher.find()) {
                   tmp = xml1.substring(matcher.start() + 1, matcher.end() - 1);
                   xml1=xml1.replace("<year>"," ");
                   xml1=xml1.replace("</year>", " ");
               }
               year = Integer.parseInt(tmp);
           }
           if (xml1.contains("<month>")) {
               Matcher matcher = pattern.matcher(xml1);
               if (matcher.find()) {
                   tmp = xml1.substring(matcher.start() + 1, matcher.end() - 1);
                   xml1=xml1.replace("<month>"," ");
                   xml1=xml1.replace("</month>", " ");
               }
               month = Integer.parseInt(tmp);
           }
           if (xml1.contains("<dayOfMonth>")) {
               Matcher matcher = pattern.matcher(xml1);
               if (matcher.find()) {
                   tmp = xml1.substring(matcher.start() + 1, matcher.end() - 1);
                   xml1=xml1.replace("<dayOfMonth>"," ");
                   xml1=xml1.replace("</dayOfMonth>", " ");
               }
               dayOfMonth = Integer.parseInt(tmp);
           }
           spaceMarine.setCreationDate(LocalDate.of(year, month, dayOfMonth));
           if (xml1.contains("<health>")) {
               Matcher matcher = pattern.matcher(xml1);
               if (matcher.find()) {
                   tmp = xml1.substring(matcher.start() + 1, matcher.end() - 1);
                   xml1=xml1.replace("<health>"," ");
                   xml1=xml1.replace("</health>", " ");
               }
               health = Float.parseFloat(tmp);
               spaceMarine.setHealth(health);
           }
           if (xml1.contains("<height>")) {
               Matcher matcher = pattern.matcher(xml1);
               if (matcher.find()) {
                   tmp = xml1.substring(matcher.start() + 1, matcher.end() - 1);
                   xml1=xml1.replace("<height>"," ");
                   xml1=xml1.replace("</height>", " ");
               }
               height = Double.parseDouble(tmp);
               spaceMarine.setHeight(height);
           }
           if (xml1.contains("<category>")) {
               Matcher matcher = pattern.matcher(xml1);
               if (matcher.find()) {
                   tmp = xml1.substring(matcher.start() + 1, matcher.end() - 1);
                   xml1=xml1.replace("<category>"," ");
                   xml1=xml1.replace("</category>", " ");
               }
               if (tmp.equalsIgnoreCase("DREADNOUGHT")) {
                   spaceMarine.setCategory(AstartesCategory.DREADNOUGHT);
               }
               if (tmp.equalsIgnoreCase("INCEPTOR")) {
                   spaceMarine.setCategory(AstartesCategory.INCEPTOR);
               }
               if (tmp.equalsIgnoreCase("LIBRARIAN")) {
                   spaceMarine.setCategory(AstartesCategory.LIBRARIAN);
               }
               if (tmp.equalsIgnoreCase("CHAPLAIN")) {
                   spaceMarine.setCategory(AstartesCategory.CHAPLAIN);
               }
               if (tmp.equalsIgnoreCase("HELIX")) {
                   spaceMarine.setCategory(AstartesCategory.HELIX);
               }
           }
           if (xml1.contains("<weaponType>")) {
               Matcher matcher = pattern.matcher(xml1);
               if (matcher.find()) {
                   tmp = xml1.substring(matcher.start() + 1, matcher.end() - 1);
                   xml1=xml1.replace("<weaponType>"," ");
                   xml1=xml1.replace("</weaponType>", " ");
               }
               if (tmp.equalsIgnoreCase("GRENADE_LAUNCHER")) {
                   spaceMarine.setWeaponType(Weapon.GRENADE_LAUNCHER);
               }
               if (tmp.equalsIgnoreCase("HEAVY_FLAMER")) {
                   spaceMarine.setWeaponType(Weapon.HEAVY_FLAMER);
               }
               if (tmp.equalsIgnoreCase("COMBI_FLAMER")) {
                   spaceMarine.setWeaponType(Weapon.COMBI_FLAMER);
               }
               if (tmp.equalsIgnoreCase("MULTI_MELTA")) {
                   spaceMarine.setWeaponType(Weapon.MULTI_MELTA);
               }
           }
            if ((xml1.contains("<nameChapter>"))) {
                Matcher matcher = pattern.matcher(xml1);
                if (matcher.find()) {
                    tmp = xml1.substring(matcher.start() + 1, matcher.end() - 1);
                    xml1=xml1.replace("<nameChapter>"," ");
                    xml1=xml1.replace("</nameChapter>", " ");
                }
                nameChapter = tmp;
            }
           if (xml1.contains("<parentLegion>")) {
               Matcher matcher = pattern.matcher(xml1);
               if (matcher.find()) {
                   tmp = xml1.substring(matcher.start() + 1, matcher.end() - 1);
                   xml1=xml1.replace("<parentLegion>"," ");
                   xml1=xml1.replace("</parentLegion>", " ");
               }
               parentLegion = tmp;
           }
           if (xml1.contains("<marinesCount>")) {
               Matcher matcher = pattern.matcher(xml1);
               if (matcher.find()) {
                   tmp = xml1.substring(matcher.start() + 1, matcher.end() - 1);
                   xml1=xml1.replace("<marinesCount>"," ");
                   xml1=xml1.replace("</marinesCount>", " ");
               }
               marinesCount = Integer.parseInt(tmp);
           }
           if (xml1.contains("<world>")) {
               Matcher matcher = pattern.matcher(xml1);
               if (matcher.find()) {
                   tmp = xml1.substring(matcher.start() + 1, matcher.end() - 1);
                   xml1=xml1.replace("<world>"," ");
                   xml1=xml1.replace("</world>", " ");
               }
               world = tmp;
           }
           spaceMarine.setChapter(new Chapter(nameChapter, parentLegion, marinesCount, world));
           spaceMarine.validate();

       }catch (WrongSpaceMarineException e){
           e.getMessage();
           System.exit(1);
       }

        return spaceMarine;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void validate() throws WrongSpaceMarineException {
        try {
            if (this.id <= 0 ) throw new WrongSpaceMarineException("ID");
            if (this.name == null || this.name.equals("")) throw new WrongSpaceMarineException("Name");
            if (this.coordinates == null) throw new WrongSpaceMarineException("Coordinates");
            if (this.creationDate == null) throw new WrongSpaceMarineException("CreationDate");
            if (this.health <= 0) throw new WrongSpaceMarineException("Health");
            if (this.weaponType == null) throw new WrongSpaceMarineException("WeaponType");
            if (this.chapter.getName() == null || this.chapter.getName().equals(""))
                throw new WrongSpaceMarineException("Chapter (Name)");
            if (this.chapter.getMarinesCount() <= 0 || this.chapter.getMarinesCount() > 1000)
                throw new WrongSpaceMarineException("Chapter (MarinesCount)");
            if (this.chapter.getWorld() == null) throw new WrongSpaceMarineException("Chapter (World)");
        } catch (WrongSpaceMarineException e) {
            e.getMessage();
            System.exit(0);
        }
    }

    public String toXml() {
        String xml;
        xml = "\t<SpaceMarine>\n" +
                "\t\t<id>" + this.getId().toString() + "</id>\n" +
                "\t\t<name>" + this.getName() + "</name>\n" +
                "\t\t<coordinates>\n" +
                "\t\t\t<x>" + this.getCoordinates().getX() + "<x>\n" +
                "\t\t\t<y>" + this.getCoordinates().getY() + "<y>\n" +
                "\t\t</coordinates>\n" +
                "\t\t<creationDate>\n" +
                "\t\t\t<year>" + this.getCreationDate().getYear() + "</year>\n" +
                "\t\t\t<month>" + this.getCreationDate().getMonthValue() + "</month>\n" +
                "\t\t\t<dayOfMonth>" + this.getCreationDate().getDayOfMonth() + "</dayOfMonth>\n" +
                "\t\t</creationDate>\n" +
                "\t\t<health>" + this.getHealth() + "</health>\n" +
                "\t\t<height>" + this.getHeight() + "</height>\n" +
                "\t\t<category>" + this.getCategory() + "</category>\n" +
                "\t\t<weaponType>" + this.getWeaponType() + "</weaponType>\n" +
                "\t\t<chapter>\n" +
                "\t\t\t<nameChapter>" + this.getChapter().getName() + "</nameChapter>\n" +
                "\t\t\t<parentLegion>" + this.getChapter().getParentLegion() + "</parentLegion>\n" +
                "\t\t\t<marinesCount>" + this.getChapter().getMarinesCount() + "</marinesCount>\n" +
                "\t\t\t<world>" + this.getChapter().getWorld() + "</world>\n" +
                "\t\t</chapter>\n" +
                "\t</SpaceMarine>\n"

        ;
        return xml;
    }

    public static SpaceMarine fillSpaceMarine(Scanner scanner) {
        SpaceMarine spaceMarine = new SpaceMarine();
        try {
            System.out.println("Ввод объекта SpaceMarine");
            spaceMarine.name = (String) ConsoleReader.conditionalRead(scanner, "Введите имя: ", false, String::toString, Objects::nonNull, (m) -> !m.equals(""));
            spaceMarine.coordinates = Coordinates.fillCoordinates(scanner);
            spaceMarine.creationDate = LocalDate.now();
            spaceMarine.health = (Float) ConsoleReader.conditionalRead(scanner, "Введите здоровье: ", false, Float::parseFloat, (m) -> Float.parseFloat(m) > 0);
            spaceMarine.height = (double) ConsoleReader.conditionalRead(scanner, "Введите рост: ", false, Double::parseDouble);
            spaceMarine.category = AstartesCategory.fillAstartesCategory(scanner);
            spaceMarine.weaponType = Weapon.fillWeapon(scanner);
            spaceMarine.chapter = Chapter.fillChapter(scanner);
            spaceMarine.id = IdGenerator.generateUniqueId();
        } catch (NoSuchElementException e) {
            System.err.println("Ну почему-ты до конца не ввел Person :(");
        } catch (FiledIncorrect filedIncorrect) {
            filedIncorrect.printStackTrace();
        }
        return spaceMarine;
    }

    public static class IdGenerator {
        public static long generateUniqueId() {
            UUID idOne = UUID.randomUUID();
            String str = "" + idOne;
            int uid = str.hashCode();
            String filterStr = "" + uid;
            str = filterStr.replaceAll("-", "");
            return Long.parseLong(str);
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Float getHealth() {
        return health;
    }

    public double getHeight() {
        return height;
    }

    public AstartesCategory getCategory() {
        return category;
    }

    public Weapon getWeaponType() {
        return weaponType;
    }

    public Chapter getChapter() {
        return chapter;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setHealth(Float health) {
        this.health = health;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setCategory(AstartesCategory category) {
        this.category = category;
    }

    public void setWeaponType(Weapon weaponType) {
        this.weaponType = weaponType;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public int compareTo(SpaceMarine p) {
        return this.getId().compareTo(p.getId());
    }

    @Override
    public String toString() {
        return  "ID:" + id +
                "\n\tName: " + name  +
                "\n\tCoordinates: " + coordinates +
                "\n\tCreationDate(YYYY-MM-DD): " + creationDate +
                "\n\tHealth: " + health +
                "\n\tHeight: " + height +
                "\n\tCategory: " + category +
                "\n\tWeaponType: " + weaponType +
                "\n\tChapter: " + chapter
                ;
    }
}
