package com.example.yuanann.stray_cat.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuanann.stray_cat.DatabaseHelper;
import com.example.yuanann.stray_cat.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Login extends AppCompatActivity {
    Button b1;
    EditText e1,e2;
    String n,p;
    TextView r,admin;
    RadioGroup ed3;
    String state="用户";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        b1 = (Button) findViewById(R.id.b1);
        e1 = (EditText) findViewById(R.id.edit1);
        e2 = (EditText) findViewById(R.id.edit2);
        ed3 = (RadioGroup) findViewById(R.id.edit3);
        r = (TextView) findViewById(R.id.regis);
        admin = (TextView) findViewById(R.id.admin);

        ed3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radbtn = (RadioButton) findViewById(checkedId);
                state="" + radbtn.getText();
            }
        });
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Login.this, Register.class);
                startActivity(intent);
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Login.this, Back_login.class);
                startActivity(intent);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = e1.getText().toString();
                String p = e2.getText().toString();
                String enter = isExistEnter(n);

                if (state.equals("用户")) {
                    if (TextUtils.isEmpty(n)) {
                        Toast.makeText(getApplicationContext(), "请输入用户名", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(p)) {
                        Toast.makeText(getApplicationContext(), "请输入密码", Toast.LENGTH_SHORT).show();
                    } else if (isExistN(n) == false) {
                        Toast.makeText(getApplicationContext(), "用户名不存在", Toast.LENGTH_SHORT).show();
                    } else if (enter.equals("n")) {
                        Toast.makeText(getApplicationContext(), "不好意思，管理员禁止了您的登陆权限", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setClass(Login.this, Login.class);
                    } else if (p.equals(isExistP(n))) {
                        Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        SharedPreferences sp = getSharedPreferences("User", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("name", n);
                        editor.putString("type", "用户");
                        editor.commit();
                        Login.this.finish();
                        String a = e1.getText().toString();
                        String b = e2.getText().toString();
                        intent.setClass(Login.this, MainActivity.class);
                        intent.putExtra("name", a);
                        startActivity(intent);
                        e1.setText("");
                        e2.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(), "登录失败,密码错误", Toast.LENGTH_SHORT).show();
                        e1.setText("");
                        e2.setText("");
                    }
                } else {
                    if (TextUtils.isEmpty(n)) {
                        Toast.makeText(getApplicationContext(), "请输入名称", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(p)) {
                        Toast.makeText(getApplicationContext(), "请输入密码", Toast.LENGTH_SHORT).show();
                    } else if (isExistPN(n) == false) {
                        Toast.makeText(getApplicationContext(), "名称不存在", Toast.LENGTH_SHORT).show();
                    } else if (p.equals(isExistP2(n))) {
                        Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        SharedPreferences sp = getSharedPreferences("User", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("name", n);
                        editor.putString("type", "公益组织");
                        editor.commit();
                        Login.this.finish();
                        String a = e1.getText().toString();
                        String b = e2.getText().toString();
                        intent.setClass(Login.this, MainActivity.class);
                        intent.putExtra("name", a);
                        startActivity(intent);
                        e1.setText("");
                        e2.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(), "登录失败,密码错误", Toast.LENGTH_SHORT).show();
                        e1.setText("");
                        e2.setText("");
                    }
                }
            }
        });

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        long num = dbHelper.allAdminNum();
        if (num == 0) {
            long i = -1;
            ContentValues values = new ContentValues();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
            Date curDate = new Date(System.currentTimeMillis());//获取当前时间
            String str = formatter.format(curDate);
            values.put("a_name", "admin");
            values.put("a_psd", "123456");
            values.put("a_time", str);
            dbHelper.insertAdmin(values);



            ContentValues values4 = new ContentValues();
            values4.put("o_sendname", "一只小熊");
            values4.put("o_pic", String.valueOf(R.drawable.f1));
            values4.put("o_demand","寻猫");
            values4.put("o_place","北京三里屯");
            values4.put("o_type","布偶猫");
            values4.put("o_content","我的小猫在三里屯走丢了,好心人找到了麻烦联系一下");
            values4.put("o_time", "2024-6-25 04:09:19");
            dbHelper.insertCircle(values4);

            values4.put("o_sendname", "我是小北");
            values4.put("o_pic", String.valueOf(R.drawable.f4));
            values4.put("o_demand","寻猫咪主人");
            values4.put("o_place","成都宽窄巷子");
            values4.put("o_type","乳白英短");
            values4.put("o_content","散步时看到一只无家可归的小猫,应该是和主人走丢了,在线找小猫咪的主人.");
            values4.put("o_time", "2024-6-25 04:09:19");
            dbHelper.insertCircle(values4);

            ContentValues values5 = new ContentValues();
            values5.put("f_sendname", "饭小慢");
            values5.put("f_pic", String.valueOf(R.drawable.n7));
            values5.put("f_state","n");
            values5.put("f_content","因为工作原因不能养猫，找一个靠谱的主人能收养它。");
            values5.put("f_time", "2024-6-25 13:30:26");
            dbHelper.insertAdopt(values5);

            values5.put("f_sendname", "羊羊");
            values5.put("f_pic", String.valueOf(R.drawable.n20));
            values5.put("f_state","n");
            values5.put("f_content","给我的小猫找主人，希望它的新主人善良有爱心。");
            values5.put("f_time", "2024-6-25 17:20:56");
            dbHelper.insertAdopt(values5);

            ContentValues values6 = new ContentValues();
            values6.put("c_fm",String.valueOf(R.drawable.n2));
            values6.put("c_name", "刚出生的小奶猫眼睛是什么颜色的？");
            values6.put("c_content","很多不了解猫咪的人都会觉得它很高冷，但是仔细接触下来，会发现猫咪身上也有很多的不同于狗狗的优点，这也可能就是人类喜欢猫咪的原因吧。\n" +
                    "\n" +
                    "第一、猫咪是名十分优秀的跳高者，猫咪一跃就能够跳出差不多像自己身高六倍高的高度，可以说是十分的厉害。\n" +
                    "\n" +
                    "第二、猫咪得智商非常的高，它们非常的聪明，从猫咪的大脑结构角度来看，它的大脑结构是不逊色的，与人脑的结构高度相似。但是因为它不会像人类一样说话，也懒于表达自己的想法。\n" +
                    "\n" +
                    "第三、猫咪有十分敏锐的听觉，猫咪的听觉能够听到的声音是人类能听到声音的四倍，能听到一些低分贝人类无法听到的声音。\n" +
                    "\n" +
                    "第四、猫咪的味觉有所偏颇，当给猫咪喂带有甜味的食物时，猫咪是品尝不出其中的甜味的。但是猫咪对水的敏感度高，品尝的出饮用水的不同味道，猫咪尤其喜欢流动的新鲜水，如果铲屎官给猫咪提前备好了温的静水的话，猫咪是不会青睐的。\n" +
                    "\n" +
                    "第五、有的铲屎官会给猫咪准备专门排泄的猫砂盆，细心观察的会发现猫咪在排泄完后，会把在猫砂盆的粑粑偷偷埋起来，有的铲屎官可能会觉得是无意的或者是淘气在玩弄，其实不是的，这是猫咪在试图掩盖自己排泄物气味，表达自己对铲屎官的听话。如果你的猫咪排泄完后并没有埋屎，那可能是它没有把你放在眼里。\n" +
                    "\n" +
                    "第六、平常在看猫咪的时候，大多从猫的种类、外表的颜色花样来发现猫咪的不同，如果大致相似就觉得差不多，殊不知每只猫咪鼻子上的纹理都是不同的，每一只猫咪都有它特别的纹理，就像每个人的指纹都一样是的。\n" +
                    "\n" +
                    "第七、铲屎官平时在养喵咪的时候会发现，猫咪有时候会喜欢用它的脸蹭你，除了想要表达喜欢和撒娇之外，猫咪用脸蹭蹭你也是想在你身上留下自己气味，觉得这样是对外界宣布自己的所有权，也是有股霸道总裁范。");
            values6.put("c_time", "2024-6-25 17:20:56");
            dbHelper.insertArticle(values6);

            values6.put("c_fm",String.valueOf(R.drawable.n11));
            values6.put("c_name", "千万不要轻易收养流浪猫");
            values6.put("c_content","有个网友给我留言，讲述了他收养流浪猫半年时间的“心酸经历”。\n" +
                    "\n" +
                    "在这半年时间里，他的心态从开始时的心疼，变成了无奈，又变成了愤怒，最后变成了后悔。\n" +
                    "\n" +
                    "尽管被流浪猫屡次伤害，可他依旧没有打算放弃；可流浪猫自己离开的行为，还是让他感到付出的努力全都浪费了。\n" +
                    "\n" +
                    "一次偶然的机会，网友在自己的车下面发现了一只流浪猫。\n" +
                    "\n" +
                    "那是一只狸花猫，发现这只猫咪的时候它很瘦弱，很脆弱，并且身上还有伤口。\n" +
                    "\n" +
                    "网友家里养了一只猫，再加上他本人喜欢猫，于是就心生怜悯把猫咪带回了家。\n" +
                    "之后，他先带着流浪猫去宠物医院处理伤口，又给猫咪打了疫苗。\n" +
                    "\n" +
                    "可是，养猫的心酸和困难才刚刚开始。\n" +
                    "\n" +
                    "这只流浪猫脾气出奇的差，尽管他付出了耐心，试图感动这只猫，可换来的是被流浪猫一次次抓挠。\n" +
                    "\n" +
                    "开始的时候，他没敢让流浪猫和自家的宠物猫接触，担心两只猫打架。" +
                    "随着时间推移，他决定试试看，两只猫是否能接受对方。\n" +
                    "\n" +
                    "没想到，刚把两只猫放在一起，流浪猫就把宠物猫咬伤了。\n" +
                    "\n" +
                    "从那之后，他再也不敢将两只猫放在一起。\n" +
                    "\n" +
                    "可是，噩耗才刚刚开始。" +
                    "这只流浪猫咬人，抓人，主动攻击他的家人；\n" +
                    "\n" +
                    "家里的垃圾桶，厨房里面的东西，家里的纸箱子，沙发，全都难逃这只猫咪的“磨爪”。\n" +
                    "\n" +
                    "无论他用什么方法，似乎都无法获得这只流浪猫的信任，无法让猫咪的暴躁之心平复下来。\n" +
                    "\n" +
                    "再后来，大概养了猫咪半年时间，猫咪自己抓坏了纱窗，从家里的2楼逃走了。\n" +
                    "\n" +
                    "从那之后，流浪猫再也没回来过。\n" +
                    "\n" +
                    "网友十分感慨：半年的付出就这样没了，又是心酸，又是心疼。\n" +
                    "\n" +
                    "他说：以后再也不敢收养流浪猫了。" );
            values6.put("c_time", "2024-6-25 17:20:56");
            dbHelper.insertArticle(values6);

            values6.put("c_fm",String.valueOf(R.drawable.n6));
            values6.put("c_name", "“流浪猫群体”到底是如何产生的？");
            values6.put("c_content","当我们在谈论流浪猫时，到底在谈论什么？\n" +
                    "爱猫并且养猫的铲屎官们，会想到慈悲，会想到同情，会想到自己家的猫；没有养过猫，不喜欢猫的人，会以冷漠的态度对待，与我无关。\n" +
                    "而仇视猫狗的人，会视它们为垃圾，做出一些伤害流浪猫狗的行为。\n" +
                    "可是没有多少人讨论，流浪猫的存在，是如何产生的？流浪猫聚集的范围和群体，是如何一点点扩大的？\n" +
                    "流浪猫群体产生的原因：\n" +
                    "第一：走丢的猫和被遗弃的猫\n" +
                    "归根结底，外面产生的流浪猫，都是从养猫的人家中主动或者被动走出去的。\n" +
                    "有些猫，是因为主人在养猫的过程中粗心大意，并没有封闭好家里的门窗，导致猫咪趁着一些机会从门窗跑出去，然后再也没回去；而这些偷偷跑出去的猫，就成为了流浪猫第一批源头；当然，自己走丢的宠物猫变成了流浪猫，毕竟是少数。\n" +
                    "更多的来源则在于，那些养猫的人，丢掉了自己家养的猫。\n" +
                    "我见过不少例子，看到过不少网友发的帖子；有些人在外面租房工作，买一只猫养来陪伴自己；辞掉工作回老家发展后，猫怎么办？没有能力带回老家，只能选择丢掉。\n" +
                    "与租房工作的人类似，还有一些在大学阶段读书的人，会养一只猫陪在自己身边；可是毕业了，需要去别的地方工作了，猫咪怎么办？\n" +
                    "那些盲目跟风养猫，看到别人养猫自己也想养一只，只是出于好奇心，却不愿意在猫咪身上投入成本的人，同样是丢猫的源头。\n" +
                    "他们不舍得给猫咪消费，不愿意在猫咪身上投资；一旦猫咪需要的，他们无法给予，最后他们就会丢掉毛。\n" +
                    "反正也不喜欢，丢掉就是了。\n" +
                    "而这些主动或被动走丢的猫咪，就是一大群流浪猫群体产生的源头。\n" );
            values6.put("c_time", "2024-6-25 17:20:56");
            dbHelper.insertArticle(values6);

            values6.put("c_fm",String.valueOf(R.drawable.n21));
            values6.put("c_name", "养流浪猫的注意事项");
            values6.put("c_content","流浪猫可爱又可怜，有的被主人遗弃，有的则是一出生就成为了流浪猫。很多人心生怜悯，去喂养或者收养流浪猫，给流浪猫一个安身之地，下面就为好心人们提供一些喂养或者收养流浪猫的建议，提醒朋友们在爱护小动物的同时也保护好自己。\n" +
                    "不要过于亲近流浪猫，喜欢是好的，不过毕竟不太清楚猫猫接触过什么病菌，如果你并不负责给猫猫洗澡澡的话，就做简单的接触就好了。接触流浪猫后一定要及时洗手，尤其是对皮肤敏感的朋友来说。\n" +
                    "注意猫咪的动作，分辨好猫猫现在的状态，不要误把警惕当卖萌，现在网上很多猫咪的视频看起来很可爱，其实猫咪当时在紧张状态或者正在生气，如果这时候你伸手去摸它，有可能会因为害怕而攻击人。\n" +
                    "与猫咪玩耍时，注意不要让猫咪误伤到自己，最好穿着长袖衣裤，不要穿着凉鞋。\n" +
                    "如果有条件，特别喜欢一只的话，建议领养回家，因为这样可以对猫咪进行更有效的管理，也能对猫咪进行更好的保护。那么领养流浪猫又应该注意什么呢？\n" +
                    "及时对猫咪进行清洗，最好用专门为宠物洗澡的沐浴露，对流浪猫进行彻底清洗，清洗时应该佩戴好手套，猫咪中的大部分是怕水的，尤其是对流浪猫咪来说，接触水有可能会让它们因为害怕而攻击人。可以使用猫咪洗澡专用的洗猫袋将猫咪装进去再洗。\n" +
                    "带猫咪去做体检，打疫苗，这是必备环节，排除猫咪带有的疾病，并做好预防工作，以便顺利过渡为家猫。\n" +
                    "结扎手术还是要做的，如果不接扎的话，猫咪在发情期间可能会到处撒尿，而且一旦乱尿留下气味后，以后它们会经常去那撒尿。\n" +
                    "购买猫砂，引导猫猫去指定的位置尿尿，如果猫猫尿到了别处，一定要擦干净，不要留下余味，原因上面已经说啦。如果耐心引导猫猫去厕所的话那就更好了，不过流浪猫收养回来毕竟都比较大了，不太容易驯服了。\n" );
            values6.put("c_time", "2024-6-25 17:20:56");
            dbHelper.insertArticle(values6);

            values6.put("c_fm",String.valueOf(R.drawable.n14));
            values6.put("c_name", "关于流浪猫的小知识");
            values6.put("c_content","你知道吗？一只流浪猫，能够吸引到多只流浪猫的聚集。\n" +
                    "尤其是在春夏季节，一只发情期的母猫，更是能够吸引到附近多只流浪猫公猫的聚集。\n" +
                    "母猫一年的繁殖次数，多达3-4次，何况猫咪每一次生产，都能生下多只小猫。\n" +
                    "当这些幼猫经历了生活的筛选，熬过了生存的难关，它们又会成长，变成新的一批流浪猫。\n" +
                    "而当猫咪超过8个月之后，一般就具备繁殖的能力；然后，通过流浪猫的不断聚集，不断繁殖，又产生新的一批流浪猫。\n" +
                    "这样的速度，是很可怕的；每一年，都会有相当庞大的幼猫在产生。\n" +
                    "如果你仔细观察就会发现：\n" +
                    "倘若你居住的环境周围，有几只流浪猫出现；这几只猫有人喂食，有居住的地方，那么过不了多久，这周围就会聚集来更多的流浪猫。\n" +
                    "流浪猫更换居住环境最大的变数在于：食物和居住地。\n");
            values6.put("c_time", "2024-6-25 17:20:56");
            dbHelper.insertArticle(values6);

            values6.put("c_fm",String.valueOf(R.drawable.n19));
            values6.put("c_name", "落难帅猫获救记");
            values6.put("c_content", "—LIMAO— \n" +
                    "\n" +
                    "///\n" +
                    "\n" +
                    "要问校园里最好看的长毛猫是谁？\n" +
                    "\n" +
                    "那一定是无德。\n" +
                    "无德是一只绝育小公猫，因为他帅气的脸庞获得了众多小姐姐的喜欢。无德虽然帅气却没有猫德，因为他仗着自己长得帅气曾在光天化日之下公然欺负小母猫，得名无德。\n" +
                    "\n" +
                    "无德之前一直生活在南校操场，但是不知道从哪天起，帅气的无德突然不见了。江湖上不见了无德的身影，但到处都是他的传说。\n" +
                    "\n" +
                    "日子就这样一天天过去了，无德渐渐消失在了大家的视野中。突然有一天，理毛志愿者听到南校管理学院教学楼中有猫咪的求救声。循着声音找过去，发现在男厕的通风管狭小缝隙中有一只猫咪，不断向外求救。\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "黑暗中用手电筒照射只能看到猫咪的眼睛，无法分辨是哪只猫咪。志愿者尝试用网兜、布条、麻袋等营救猫咪，均未成功。当时天色已晚，志愿者只好作罢。\n" +
                    "\n" +
                    "第二天，理毛联系老师向学校相关部门求助。工作人员对现场情况分析后，决定拆窗营救。\n" +
                    "进入猫咪被困的管道里后发现，被困猫咪居然是大名鼎鼎的无德！无德此时已经被吓坏了，不知道在通风管里被困了多久，甚至还被吓尿了。\n" +
                    "但是为了无德潇洒帅猫的人设，理毛并未将此事告诉无德的迷妹们。\n" +
                    "\n" +
                    "得救的无德在短暂休息后再次消失在了大家的视野里，希望无德以后不要再做这么失德的事情了，否则理毛花再多的钱也难以公关掉无德的黑料了。\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "在此理毛向三位协助的工人师傅表示衷心的感谢，他们分别是：倪建军、胡思宇和杨运义，感谢他们对弱小生命的爱护与协助！\n" +
                    "");
            values6.put("c_time", "2024-6-25 19:30:25");
            dbHelper.insertArticle(values6);

            values6.put("c_fm",String.valueOf(R.drawable.n18));
            values6.put("c_name", "如果你打算收养流浪猫，该做哪些准备？");
            values6.put("c_content","在这个天气下，很多养猫的铲屎官都会对外面的流浪猫心生怜悯。\n" +
                    "\n" +
                    "每次看到流浪猫在垃圾桶周围寻找食物，他们就忍不住拿出来一些猫粮喂食流浪猫。\n" +
                    "\n" +
                    "有条件的人，会选择收养流浪猫。\n" +
                    "\n" +
                    "可是，并不是每一只流浪猫都适合收养。\n" +
                    "\n" +
                    "也有一部分流浪猫，它们从小就生活在外面，没有安全感，对人类有着强烈的敌意。\n" +
                    "\n" +
                    "一旦有人靠近，猫咪就会强烈地反抗，甚至有攻击人的行为。\n" +
                    "\n" +
                    "这样的猫咪，真的很难收养。\n" +
                    "\n" +
                    "要知道，即便是家养的宠物猫，也没有完全被人类驯服，更何况一只从小居无定所的流浪猫呢？" +
                    "第一：最好收养幼猫\n" +
                    "\n" +
                    "幼猫的发育还不够成熟，你有大把的时间让幼猫信任你，逐渐熟悉被你圈养在家里的感觉。\n" +
                    "\n" +
                    "而作为一只成年的流浪猫，它们很难对你建立起信任度。\n" +
                    "\n" +
                    "第二：收养流浪猫，最好有足够的心理准备\n" +
                    "\n" +
                    "如果你有养猫的经验和经历，物质方面是不需要担心的；但你需要做好心理上的准备。\n" +
                    "\n" +
                    "流浪猫可能会攻击人，可能会不信任你，可能在家里闯祸。\n" +
                    "\n" +
                    "如果你家里已经养了猫，那么流浪猫很可能无法与你家猫“好好相处”。\n" +
                    "\n" +
                    "这些都是你应该考虑到的问题。\n" +
                    "\n" +
                    "\n" +
                    "当然，你居住的空间应该足够大。\n" +
                    "\n" +
                    "因为流浪猫刚刚被圈养在家里，往往缺少安全感，害怕，会找个地方躲起来。\n" +
                    "\n" +
                    "它们的应激反应一旦严重，可能是失控的状态，这些都需要你做好准备。\n" +
                    "\n" +
                    "如果你没有养宠物的经验，千万不要轻易收养流浪猫，因为你很难跟它们好好相处。\n" +
                    "\n" +
                    "喜欢宠物，想要给流浪猫一些帮助，这是一个人善良的表现。\n" +
                    "\n" +
                    "但善良，应该建立在有足够准备的前提下。");
            values6.put("c_time", "2024-6-25 17:20:56");
            dbHelper.insertArticle(values6);

            values6.put("c_fm",String.valueOf(R.drawable.n12));
            values6.put("c_name", "饭饭妈，你的孩子丢了！");
            values6.put("c_content","饭饭妈是一只小小的狸花猫，因为的她的女儿饭饭非常可爱，像一只活泼的小狗，所以大家都叫她饭饭妈。\n" +
                    "话说饭饭妈在寒假期间又怀孕了，开学后没多久，饭饭妈就一连生下了八只小猫。\n" +
                    "饭饭妈尽职尽责，只要有空就出来找吃的，回去给八只小奶猫喂奶。但是因为饭饭妈性格胆小，不太亲人，总是吃不到太多，整个猫都饿瘦了。\n" +
                    "\n" +
                    "过了几天后，理毛志愿者发现饭饭妈把孩子藏起来了！但是粗心的饭饭妈丢下了一只黑色小猫，其余的七只都不知所踪。\n" +
                    "志愿者只好暂时收留了这只可爱的小猫，照顾她。收留奶猫的志愿者同时也收留了饭饭，看到自己的亲妹妹饭饭一脸震惊。\n" +
                    "饭饭妈尽职尽责，只要有空就出来找吃的，回去给八只小奶猫喂奶。但是因为饭饭妈性格胆小，不太亲人，总是吃不到太多，整个猫都饿瘦了。\n" +
                    "\n" +
                    "过了几天后，理毛志愿者发现饭饭妈把孩子藏起来了！但是粗心的饭饭妈丢下了一只黑色小猫，其余的七只都不知所踪。\n" +
                    "志愿者只好暂时收留了这只可爱的小猫，照顾她。收留奶猫的志愿者同时也收留了饭饭，看到自己的亲妹妹饭饭一脸震惊。\n" );
            values6.put("c_time", "2024-6-25 17:20:56");
            dbHelper.insertArticle(values6);

            ContentValues values7 = new ContentValues();
            int number = 0;
            Random random = new Random();
            for (int a = 0;a < 10; a++) {
                // 生成 0-9 随机整数
                number = random.nextInt(3000);
            }
            values7.put("c_fm",String.valueOf(R.drawable.n1));
            values7.put("c_name","志愿者招募｜感谢，一【撸】有你～");
            values7.put("c_man", number);
            values7.put("c_content","它们是落入人间的小天使\n" +
                    "\n" +
                    "从一出生就流落在街头\n" +
                    "\n" +
                    "招募\n" +
                    "\n" +
                    "暑假流浪猫志愿者啦\n" +
                    "\n" +
                    "它们是落入人间的小天\n" +
                    "\n" +
                    "从一出生就流落在街头\n" +
                    "\n" +
                    "招募\n" +
                    "\n" +
                    "暑假流浪猫志愿者啦n" +
                    "\n" +
                    "这个夏天\n" +
                    "\n" +
                    "我想和你一起请猫猫吃顿饭\n" +
                    "\n" +
                    "你请客\n" +
                    "\n" +
                    "我们买单\n" +
                    "\n" +
                    "关于培训\n" +
                    "\n" +
                    "1、培训时间：7月8、9、10号下午4：00-6：00（任选一天）。\n" +
                    "\n" +
                    "2、本次培训为实地培训。培训环节十分重要，请确保自己有时间参加培训再进行报名，否则将不安排上岗。\n" +
                    "\n" +
                    "之前参加过活动的志愿者可以不参加此次培训。\n" +
                    "\n" +
                    "志愿内容：每天下午4：00-6：00在主校区和东校区固定的喂猫点给交大的流浪猫喂猫粮。\n" +
                    "\n" +
                    "志愿周期：7月11号--8月30号（无需全部时间都在北京，某一段即可）\n" +
                    "\n" +
                    "排班：每周活动开始前，将统计下一周有时间的志愿者，并于周日前发放排班表）。\n");
            values7.put("c_time", str);
            dbHelper.insertRe(values7);

            for (int a = 0;a < 10; a++) {
                // 生成 0-9 随机整数
                number = random.nextInt(3000);
            }
            values7.put("c_fm",String.valueOf(R.drawable.n22));
            values7.put("c_name","流浪猫志愿者招募啦~");
            values7.put("c_man", number);
            values7.put("c_content","看了这么多可爱的毛孩子\n" +
                    "你心动了吗\n" +
                    "虽然作为学生 我们还没有条件给他们一个家\n" +
                    "但我们也可以帮助他们哦\n" +
                    "来吧 成为志愿者\n" +
                    "帮助他们找到一个温暖的家\n" +
                    "做志愿者到底是什么感觉呢?\n" +
                    "挺累的,一次就要花掉一整个下午\n" +
                    "天气又冷，有时候冻得手指都没知觉了\n" +
                    "来回路程也不近,谁不想在周末好好休息\n" +
                    "但也挺开心的,被毛孩子们围绕着\n" +
                    "听听救助人和ta的故事\n" +
                    "感受无私单纯的爱与付\n" +
                    "\n" +
                    "报名志愿者有什么条件吗？\n" +
                    "须为完全民事行为能力人，有正确的价值观、较强的责任感，有善待动物、尊重生命的基本素养。\n" +
                    "志愿者的频次大约为每月一次到两次，一般为周六下午。出于团队配合默契、经验积累和沟通效率的因素，会优先考虑可长期参与的志愿者（指大部分场次可以来，但也允许偶尔请假。）\n" +
                    "志愿者最好是有养宠经验、了解领养、办证、绝育等流程。\n" +
                    "最最最重要的是，我们需要的是真正甘愿付出、不计回报的志愿者。\n" +
                    "救助这条路，道阻且长，没有足够的爱和毅力，注定无果。\n" +
                    "能收获的就只有 爱和温暖。");
            values7.put("c_time", str);
            dbHelper.insertRe(values7);

            for (int a = 0;a < 10; a++) {
                // 生成 0-9 随机整数
                number = random.nextInt(3000);
            }
            values7.put("c_fm",String.valueOf(R.drawable.n24));
            values7.put("c_name","校园流浪猫喂养志愿活动");
            values7.put("c_man", number);
            values7.put("c_content","【具体内容】\n" +
                    "\n" +
                    "1、每天喂食\n" +
                    "\n" +
                    "2、每天换水\n" +
                    "\n" +
                    "3、每两天清洗食盆、水盆\n" +
                    "\n" +
                    "4、每两天打扫一次卫生（扫地、清理周围垃圾和鸟粪）\n" +
                    "\n" +
                    "5、每两天擦拭猫窝\n" +
                    "\n" +
                    " 【志愿时长】\n" +
                    "\n" +
                    "一天一次，一次一小时\n" +
                    "\n" +
                    "（以实际志愿天数为准）\n" +
                    "\n" +
                    " 【志愿者要求】\n" +
                    "\n" +
                    "1、志愿者无动物毛发过敏病史，有爱心，不怕脏\n" +
                    "\n" +
                    "2、每天打卡拍照（食物和志愿者都入镜）\n" +
                    "\n" +
                    " 【志愿情况说明】\n" +
                    "\n" +
                    "1、喂食时间不限，选择个人每天空闲时间前往即可\n" +
                    "\n" +
                    "2、猫粮、水、打扫工具皆有提供，志愿者无须自带\n" +
                    "\n" +
                    "3、流浪猫野性较大，不熟悉猫咪的志愿者切勿抚摸，以免被猫抓咬\n" +
                    "\n" +
                    " 【志愿培训】\n" +
                    "\n" +
                    "5月1日线下\n" +
                    "\n" +
                    "（如有更改，将在志愿者群通知）");
            values7.put("c_time", str);
            dbHelper.insertRe(values7);



        }
    }

    private boolean isExistN(String name) {
        boolean a=true;
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cursor mycursor=dbHelper.queryUser(name);
        if (mycursor.moveToFirst() == false)
        {
            a=false;
        }
        return a;
    }

    private boolean isExistPN(String name) {
        boolean a=true;
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cursor mycursor=dbHelper.queryPubilic(name);
        if (mycursor.moveToFirst() == false)
        {
            a=false;
        }
        return a;
    }

    private String isExistEnter(String name) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cursor cursor=dbHelper.  queryUser(name);
        String state=null;
        while(cursor.moveToNext()){
            //moveToNext()移动光标到下一行
            state= cursor.getString(cursor.getColumnIndex("u_state"));

        }
        return state;
    }

    private String isExistP(String name) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cursor cursor=dbHelper.  queryUser(name);

        String psd=null;
        while(cursor.moveToNext()){
            //moveToNext()移动光标到下一行
            psd = cursor.getString(cursor.getColumnIndex("u_psd"));

        }
        return psd;
    }

    private String isExistP2(String name) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cursor cursor=dbHelper.  queryPubilic(name);

        String psd=null;
        while(cursor.moveToNext()){
            //moveToNext()移动光标到下一行
            psd = cursor.getString(cursor.getColumnIndex("a_psd"));

        }
        return psd;
    }



}