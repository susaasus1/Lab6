package Data;

import Exceptions.FiledIncorrect;
import Exceptions.IdNotUnique;
import Exceptions.RightException;
import Exceptions.SameIdException;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpaceMarines extends LinkedList<SpaceMarine> {
    private LocalDate initDate=LocalDate.now();
    private File file;
    private BufferedReader bufReader;
    private String line;
    LinkedList<SpaceMarine> spaceMarineLinkedList = new LinkedList<>();
    private final String source;

    public SpaceMarines(String source) throws FileNotFoundException {
        this.source = source;
        file = new File(source);
        try{
            Reader fileReader = new FileReader(file);
            bufReader = new BufferedReader(fileReader);
        } catch (IOException e) {
            System.err.println("Файл не найден");
            System.exit(0);
        }

    }

    public void parse() throws IOException, SameIdException {
       try{ if (!file.canRead() && !file.canWrite() && file.exists()) throw new RightException("Возникла ошибка с правами файла");
        if (!file.canRead() && file.exists()) throw new RightException("Файл не удалось прочитать");
        if (!file.canWrite() && file.exists()) throw new RightException("В файл не получиться что-либо записать");
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line;
        String xml=null;
        while ((line=reader.readLine())!=null){
        if (line.contains("</SpaceMarine>")) {
            this.offer(SpaceMarine.parseSpaceMarine(xml));
            xml=null;
            continue;
        }
        xml=xml+line+"\n";
        }
           fileReader.close();
           reader.close();
       } catch (RightException | FiledIncorrect e) {
           e.printStackTrace();
       }
        HashSet<Long> idList = new HashSet<Long>();
        for (SpaceMarine p : this) { ;
            if (!idList.isEmpty()) {if (idList.contains(p.getId())) {throw new SameIdException();}}
            idList.add(p.getId());
        }
    }



    public void save() {
        try (FileOutputStream fout = new FileOutputStream(file)) {
            byte[] buffer = "<?xml version=\"1.0\"?>\n".getBytes();
            fout.write(buffer, 0, buffer.length);
            buffer = "<SpaceMarines>\n".getBytes();
            fout.write(buffer, 0, buffer.length);
            for (SpaceMarine spaceMarine : this) {
                buffer = "\t<SpaceMarine>\n".getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t<id>" + spaceMarine.getId() + "</id>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t<name>" + spaceMarine.getName() + "</name>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t<coordinates>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t\t<x>" + spaceMarine.getCoordinates().getX() + "<x>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t\t<y>" + spaceMarine.getCoordinates().getY() + "<y>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t</coordinates>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t<creationDate>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t\t<year>" + spaceMarine.getCreationDate().getYear() + "</year>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t\t<month>" + spaceMarine.getCreationDate().getMonthValue() + "</month>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t\t<dayOfMonth>" + spaceMarine.getCreationDate().getDayOfMonth() + "</dayOfMonth>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t</creationDate>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t<health>" + spaceMarine.getHealth() + "</health>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t<height>" + spaceMarine.getHeight() + "</height>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t<category>" + spaceMarine.getCategory() + "</category>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t<weaponType>" + spaceMarine.getWeaponType() + "</weaponType>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t<chapter>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t\t<nameChapter>" + spaceMarine.getChapter().getName() + "</nameChapter>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t\t<parentLegion>" + spaceMarine.getChapter().getParentLegion() + "</parentLegion>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t\t<marinesCount>" + spaceMarine.getChapter().getMarinesCount() + "</marinesCount>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t\t<world>" + spaceMarine.getChapter().getWorld() + "</world>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t</chapter>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = "\t</SpaceMarine>\n".getBytes();
                fout.write(buffer, 0, buffer.length);
            }
            buffer = "</SpaceMarines>\n".getBytes();
            fout.write(buffer, 0, buffer.length);
            System.out.println("Коллекция успешно сохранена!");
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден");
        } catch (IOException e) {
            System.err.println("Проверьте файл");
        }
        try {
            if (!file.canWrite()) throw new SecurityException();
        }catch (SecurityException exception){
            System.out.println("Файл защищен от записи");
        }

    }

    public void remove(Long id) throws NotFound {
        SpaceMarine[] array = this.toArray(new SpaceMarine[0]);
        List<SpaceMarine> list = new ArrayList<>(Arrays.asList(array));

        for (SpaceMarine spaceMarine : list) {
            if (spaceMarine.getId() == id) {
                list.remove(spaceMarine);
                this.clear();
                this.addAll(list);
                return;
            }
        }

        throw new NotFound();
    }

    public void update(Long id, SpaceMarine spaceMarine) throws NotFound {
        SpaceMarine[] array = this.toArray(new SpaceMarine[0]);
        List<SpaceMarine> list = new ArrayList<>(Arrays.asList(array));

        for (SpaceMarine p : list) {
            if ( (long) p.getId() == id) {
                spaceMarine.setId(id);
                list.set(list.indexOf(p), spaceMarine);
                this.clear();
                this.addAll(list);
                return;
            }
        }

        throw new NotFound();
    }


    @Override
    public String toString() {
        return  "Тип коллекции: LinkedList\n" +
                "Дата инициализации: " + initDate.toString() + "\n" +
                "Количество элементов: " + this.size();
    }

}
