package com.pullein.demo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonArray;
import com.pullein.common.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class JsonActivity extends AppCompatActivity {

    private TextView tvText;

    private String jsonBean = "{\"age\":\"123\",\"name\":\"xiao\"}";
    private String jsonArray = "[{\"age\":\"123\",\"name\":\"xiao\"},{\"age\":\"12\",\"name\":\"li\"},{\"age\":\"23\",\"name\":\"zhao\"},{\"age\":\"13\",\"name\":\"wang\"}]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        tvText = findViewById(R.id.tvText);
        Bean bean1 = new Bean("xiao", "123");
        Bean bean2 = new Bean("li", "12");
        Bean bean3 = new Bean("zhao", "23");
        Bean bean4 = new Bean("wang", "13");
        List<Bean> list = new ArrayList<>();
        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
        list.add(bean4);
        List<Bean> list2 = new ArrayList<>();
        tvText.setText(JsonUtil.toJson(bean1));
        tvText.append("\n");
        tvText.append(JsonUtil.toJson(list));
        tvText.append("\n");
        tvText.append(JsonUtil.toJson(list2));
        tvText.append("\n");
        tvText.append(JsonUtil.toBean(jsonBean, Bean.class).toString());
        tvText.append("\n");
        tvText.append(JsonUtil.toList(jsonArray, Bean.class).toString());
        tvText.append("\n");
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(jsonArray);
        tvText.append(JsonUtil.toList(jsonArray, Bean.class).toString());
    }

    class Bean {
        private String name;
        private String age;

        public Bean(String name, String age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Bean{" +
                    "name='" + name + '\'' +
                    ", age='" + age + '\'' +
                    '}';
        }
    }
}
