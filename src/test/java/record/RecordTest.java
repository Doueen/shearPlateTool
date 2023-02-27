package record;

import com.google.gson.Gson;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-02-26 17:29
 */

public class RecordTest {


    public static void main(String[] args) {
        Record record =new Record(LocalDateTime.now());
        record.setData("jsdnjg");
        Record record1=new Record(LocalDateTime.now());
        record1.setData("jsdnjg6");
        Records records=new Records(LocalDate.now());
        records.addRecord(record);
        records.addRecord(record1);
        System.out.println(new Yaml(new Constructor(Records.class)).dumpAs(records, Tag.MAP,null));
        System.out.println(new Yaml().dump(record1));
        String yamlstr=new Yaml(new Constructor(Records.class)).dumpAs(records,Tag.MAP,null);
        Map records1=new Yaml().load(yamlstr);
       List<Record> list=new Gson().fromJson(records1.get("records").toString(),List.class);
        System.out.println(list.get(0).getData());

    }
}