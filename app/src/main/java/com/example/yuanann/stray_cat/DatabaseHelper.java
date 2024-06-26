package com.example.yuanann.stray_cat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="stray";//定义数据库名
    private static final String TABLE_NAME="user";//定义表名
    private static final String CREATE_TABLE="create table "+TABLE_NAME+"( _id integer primary key autoincrement,u_name text,u_psd text,u_state text,u_time text)";//创建表
    private static final String TABLE_NAME2="recruit";//定义表名(new)
    private static final String CREATE_TABLE2="create table "+TABLE_NAME2+"(_id integer primary key autoincrement,c_name,c_fm,c_time,c_man,c_content)";//创建表
    private static final String TABLE_NAME3="adopt";//定义表名(new)
    private static final String CREATE_TABLE3="create table "+TABLE_NAME3+"(_id integer primary key autoincrement,f_sendname,f_content,f_state,f_pic,f_receivename,f_time)";//创建表
    private static final String TABLE_NAME4="circle";//定义表名
    private static final String CREATE_TABLE4="create table "+TABLE_NAME4+"(_id integer primary key autoincrement,o_sendname,o_demand,o_place,o_type,o_content,o_time,o_pic)";//创建表
    private static final String TABLE_NAME5="circle_comment";//定义表名
    private static final String CREATE_TABLE5="create table "+TABLE_NAME5+"(_id integer primary key autoincrement,oc_oid integer,oc_uname,oc_time,oc_content,oc_state)";//创建表
    private static final String TABLE_NAME6="myapply";//定义表名(new)
    private static final String CREATE_TABLE6="create table "+TABLE_NAME6+"(_id integer primary key autoincrement,m_vid integer,m_vname,m_uname,m_content,m_state,m_time)";//创建表
    private static final String TABLE_NAME7="donate";//定义表名(new)
    private static final String CREATE_TABLE7="create table "+TABLE_NAME7+"(_id integer primary key autoincrement,d_name,d_money,d_time)";//创建表
    private static final String TABLE_NAME8="voluntary";//定义表名(new)
    private static final String CREATE_TABLE8="create table "+TABLE_NAME8+"(_id integer primary key autoincrement,v_uname,v_rid,v_rname,v_time)";//创建表
    private static final String TABLE_NAME9="article";//定义表名
    private static final String CREATE_TABLE9="create table "+TABLE_NAME9+"(_id integer primary key autoincrement,c_name,c_fm,c_content,c_time)";//创建表
    private static final String TABLE_NAME10="myarticle";//定义表名
    private static final String CREATE_TABLE10="create table "+TABLE_NAME10+"(_id integer primary key autoincrement,m_vid integer,m_uname)";//创建表
    private static final String TABLE_NAME11="admin";//定义表名
    private static final String CREATE_TABLE11="create table "+TABLE_NAME11+"( _id integer primary key autoincrement,a_name text,a_psd text,a_time)";
    private static final String TABLE_NAME12="staff";//定义表名(new)
    private static final String CREATE_TABLE12="create table "+TABLE_NAME12+"( _id integer primary key autoincrement,a_name text,a_psd text,a_time)";


    //创建SQLiteDatabase实例
    private SQLiteDatabase db;
    //构造方法
    public DatabaseHelper(Context context){
        super(context,DB_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //实例化SQLiteDatabase
        db=sqLiteDatabase;
        //创建表
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE2);
        db.execSQL(CREATE_TABLE3);
        db.execSQL(CREATE_TABLE4);
        db.execSQL(CREATE_TABLE5);
        db.execSQL(CREATE_TABLE6);
        db.execSQL(CREATE_TABLE7);
        db.execSQL(CREATE_TABLE8);
        db.execSQL(CREATE_TABLE9);
        db.execSQL(CREATE_TABLE10);
        db.execSQL(CREATE_TABLE11);
        db.execSQL(CREATE_TABLE12);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db=sqLiteDatabase;
        //创建表
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS recruit");
        db.execSQL("DROP TABLE IF EXISTS adopt");
        db.execSQL("DROP TABLE IF EXISTS circle");
        db.execSQL("DROP TABLE IF EXISTS circle_comment");
        db.execSQL("DROP TABLE IF EXISTS myapply");
        db.execSQL("DROP TABLE IF EXISTS donate");
        db.execSQL("DROP TABLE IF EXISTS voluntary");
        db.execSQL("DROP TABLE IF EXISTS article");
        db.execSQL("DROP TABLE IF EXISTS myarticle");
        db.execSQL("DROP TABLE IF EXISTS admin");
        db.execSQL("DROP TABLE IF EXISTS staff");
        onCreate(db);

    }
    //添加user表数据
    public void insert(ContentValues values){
        //将数据库设置为可读写的
        db=getWritableDatabase();
        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    //添加user表数据
    public void insertAdmin(ContentValues values){
        //将数据库设置为可读写的
        db=getWritableDatabase();
        db.insert(TABLE_NAME11,null,values);
        db.close();
    }
    //添加task表数据
    public void insertRe(ContentValues values){
        //将数据库设置为可读写的
        db=getWritableDatabase();
        db.insert(TABLE_NAME2,null,values);
        db.close();
    }
    //添加course_comment表数据
    public void insertAdopt(ContentValues values){
        //将数据库设置为可读写的
        db=getWritableDatabase();
        db.insert(TABLE_NAME3,null,values);
        db.close();
    }
    //添加cicle表数据
    public void insertCircle(ContentValues values){
        //将数据库设置为可读写的
        db=getWritableDatabase();
        db.insert(TABLE_NAME4,null,values);
        db.close();
    }
    //添加circle_comment表数据
    public void insertCircle_comment(ContentValues values){
        //将数据库设置为可读写的
        db=getWritableDatabase();
        db.insert(TABLE_NAME5,null,values);
        db.close();
    }
    //添加topic_comment表数据
    public void insertMyapply(ContentValues values){
        //将数据库设置为可读写的
        db=getWritableDatabase();
        db.insert(TABLE_NAME6,null,values);
        db.close();
    }
    //添加topic_comment表数据
    public void insertDonate(ContentValues values){
        //将数据库设置为可读写的
        db=getWritableDatabase();
        db.insert(TABLE_NAME7,null,values);
        db.close();
    }
    //添加topic_comment表数据
    public void insertVol(ContentValues values){
        //将数据库设置为可读写的
        db=getWritableDatabase();
        db.insert(TABLE_NAME8,null,values);
        db.close();
    }
    //添加article表数据
    public void insertArticle(ContentValues values){
        //将数据库设置为可读写的
        db=getWritableDatabase();
        db.insert(TABLE_NAME9,null,values);
        db.close();
    }
    //添加article表数据
    public void insertMyarticle(ContentValues values){
        //将数据库设置为可读写的
        db=getWritableDatabase();
        db.insert(TABLE_NAME10,null,values);
        db.close();
    }
    //添加staff表数据
    public void insertStaff(ContentValues values){
        //将数据库设置为可读写的
        db=getWritableDatabase();
        db.insert(TABLE_NAME12,null,values);
        db.close();
    }
    //修改数据
    public void updateUserSex(String name,String sex,String head){
        db=getWritableDatabase();
        //获得对象的属性
        ContentValues values = new ContentValues();
        values.put("u_sex",sex);//key为字段名，value为值
        values.put("u_head",head);//key为字段名，value为值
        db.update(TABLE_NAME, values, "u_name=?", new String[]{name});
        db.close();
    }
    //修改数据2
    public void updateUserInfo(String name,String w,String l,String b,String i){
        db=getWritableDatabase();
        //获得对象的属性
        ContentValues values = new ContentValues();
        values.put("u_weight",w);//key为字段名，value为值
        values.put("u_long",l);//key为字段名，value为值
        values.put("u_birth",b);//key为字段名，value为值
        values.put("u_info",i);//key为字段名，value为值
        db.update(TABLE_NAME, values, "u_name=?", new String[]{name});
        db.close();
    }
    //删除数据
    public void del(int id){
        db=getWritableDatabase();
        db.delete(TABLE_NAME,"_id=?",new String[]{String.valueOf(id)});
        db.close();
    }
    //删除申请
    public void delAdopt(int id){
        db=getWritableDatabase();
        db.delete(TABLE_NAME3,"_id=?",new String[]{String.valueOf(id)});
        db.close();
    }
    //查询用户
    public Cursor query(){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME,null,null,null,null,null,"_id asc");
        //db.close();
        return cursor;
    }

    //查询志愿者
    public Cursor queryVol(){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME8,null,null,null,null,null,"_id asc");
        //db.close();
        return cursor;
    }

    //查询新闻
    public Cursor querynews(){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME9,null,null,null,null,null,"_id asc");
        //db.close();
        return cursor;
    }

    //查询管理员
    public Cursor queryAdmin(){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME11,null,null,null,null,null,"_id asc");
        //db.close();
        return cursor;
    }

    //查询所有志愿活动
    public Cursor queryRe(){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME2,null,null,null,null,null,"_id asc");
        //db.close();
        return cursor;
    }

    //查询课程评论
    public Cursor queryCourse_comment(){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME3,null,null,null,null,null,"_id desc");
        //db.close();
        return cursor;
    }

    //查询公益组织
    public Cursor queryStaff(){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME12,null,null,null,null,null,"_id asc");
        //db.close();
        return cursor;
    }

    //查询新闻
    public Cursor queryDonate(){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME7,null,null,null,null,null,"_id asc");
        //db.close();
        return cursor;
    }

    //查询文章
    public Cursor queryArticle(){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME9,null,null,null,null,null,"_id asc");
        //db.close();
        return cursor;
    }

    //根据名称查询对应收藏数据
    public Cursor querymy_col(String name){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME6,null,"m_uname like ?", new String[] { name+"" },null,null,"_id asc");
        //db.close();
        return cursor;
    }

    //根据名称查询对应关注数据
    public Cursor querymy_foucs(String name){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME10,null,"m_uname like ?", new String[] { name+"" },null,null,"_id asc");
        //db.close();
        return cursor;
    }

    //根据用户名查询对应用户id
    public Cursor queryuser_id(String name){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME,null,"u_name like ?", new String[] { name+"" },null,null,"_id asc");
        //db.close();
        return cursor;
    }

    //根据用户名查询对应用户id
    public Cursor queryadmin_id(String name){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME11,null,"a_name like ?", new String[] { name+"" },null,null,"_id asc");
        //db.close();
        return cursor;
    }

    //根据用户id查询对应用户id
    public Cursor queryuser_myid(String id){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME,null,"_id like ?", new String[] { id+"" },null,null,"_id asc");
        //db.close();
        return cursor;
    }

    //根据用户id查询对应管理员id
    public Cursor queryadin_myid(String id){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME12,null,"_id like ?", new String[] { id+"" },null,null,"_id asc");
        //db.close();
        return cursor;
    }

    //根据用户id查询对应课程id
    public Cursor querycourse_myid(String id){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME2,null,"_id like ?", new String[] { id+"" },null,null,"_id asc");
        //db.close();
        return cursor;
    }

    //根据名称查询对应加入的课程
    public Cursor querymy_cou(String name){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME8,null,"m_uname like ?", new String[] { name+"" },null,null,"_id asc");
        //db.close();
        return cursor;
    }

    //查询用户发布的朋友圈
    public Cursor queryMynews(String id){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME9,null,"_id like ?", new String[] { id+"" },null,null,"_id desc");
        //db.close();
        return cursor;
    }

    //查询用户发布的朋友圈
    public Cursor queryRe(String id){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME2,null,"_id like ?", new String[] { id+"" },null,null,"_id desc");
        //db.close();
        return cursor;
    }

    //查询用户自己发布的朋友圈
    public Cursor queryCircle_user(String name){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME4,null,"o_sendname like ?", new String[] { name+"" },null,null,"_id desc");
        //db.close();
        return cursor;
    }

    //查询用户自己的收养
    public Cursor queryAdopt_user(String name){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME6,null,"m_uname like ?", new String[] { name+"" },null,null,"_id desc");
        //db.close();
        return cursor;
    }

    //查询用户自己的收养
    public Cursor queryStaff(String name){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME12,null,"a_name like ?", new String[] { name+"" },null,null,"_id asc");
        //db.close();
        return cursor;
    }

    //查询用户自己的收养
    public Cursor queryDonatet_user(String name){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME7,null,"d_name like ?", new String[] { name+"" },null,null,"_id desc");
        //db.close();
        return cursor;
    }

    //查询用户自己的收养
    public Cursor queryAdoptAdd_user(String name){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME3,null,"f_sendname like ?", new String[] { name+"" },null,null,"_id desc");
        //db.close();
        return cursor;
    }

    //查询用户自己的收养
    public Cursor queryVol(String name){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME8,null,"v_uname like ?", new String[] { name+"" },null,null,"_id desc");
        //db.close();
        return cursor;
    }

    //查询用户发布的朋友圈
    public Cursor queryCircle(){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME4,null,null,null,null,null,"_id desc");
        //db.close();
        return cursor;
    }

    //查询用户发布的朋友圈
    public Cursor queryCircle_Admin(){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME4,null,null,null,null,null,"_id asc");
        //db.close();
        return cursor;
    }

    //查询系统用户发布的朋友圈
    public Cursor queryCircle_System(){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME4,null,"_id <= 4",null,null,null,"_id desc");
        //db.close();
        return cursor;
    }

    //根据名称搜索对应视频数据
    public Cursor querynews_search(String name){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME9,null,"c_name like ?", new String[] { "%"+name+"%" },null,null,"_id asc");
        //db.close();
        return cursor;
    }

    //查询朋友圈评论
    public Cursor queryCircle_comment(){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME5,null,null,null,null,null,"_id desc");
        //db.close();
        return cursor;
    }

    //查询收藏
    public Cursor queryMyapply(){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME6,null,null,null,null,null,"_id desc");
        //db.close();
        return cursor;
    }

    //查询朋友圈点赞
    public Cursor queryCircle_nice(){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME7,null,null,null,null,null,"_id desc");
        //db.close();
        return cursor;
    }

    //查询收藏
    public Cursor queryMycourse(){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME8,null,null,null,null,null,"_id desc");
        //db.close();
        return cursor;
    }

    //查询申请
    public Cursor queryApply(){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME6,null,null,null,null,null,"_id desc");
        //db.close();
        return cursor;
    }

    //查询关注
    public Cursor queryMyarticle(){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME10,null,null,null,null,null,"_id desc");
        //db.close();
        return cursor;
    }

    //查询学生共有多少
    //
    public long allStudentNum( ){
        db=getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(_id) from user where u_type='学生'",null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    //根据话题查找对应评论的条数
    public long allcircle_commentNum(int topic_id){
        db=getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(_id) from circle_comment where oc_oid="+topic_id,null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    //通过用户领养
    public void updatuserApply(String id){
        //获得对象的属性
        db=getWritableDatabase();
        //获得对象的属性
        ContentValues values = new ContentValues();
        values.put("m_state","已通过");//key为字段名，value为值
        db.update(TABLE_NAME6, values, "_id=?", new String[]{id});
        db.close();
    }

    //根据用户查找对应历史的条数
    public long allhistoryNum(String name){
        db=getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(_id) from history where h_sname='"+name+"'",null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    //修改话题评论条数数据
    public void updatecircle_comment_num(String id,String num){
        db=getWritableDatabase();
        //获得对象的属性
        ContentValues values = new ContentValues();
        values.put("oc_state",num);//key为字段名，value为值
        db.update(TABLE_NAME5, values, "_id=?", new String[]{id});
        db.close();
    }

    //根据话任务点击数确定对应话题和用户总条数
    public long alltask_read(String name,int id){
        db=getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(_id) from taskread where r_sname='"+name+"' and r_tid="+id,null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    //根据话任务点击数确定对应话题和用户总条数
    public long alltask_readnum(String j){
        db=getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(_id) from taskread where r_state='"+j+"'",null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    //根据话视频点击数确定最近历史数据
    public long allhistorynum(String name,int id){
        db=getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(_id) from history where h_vid='"+id+"' and h_sname='"+name+"'",null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    //修改任务已读条数数据
    public void updatask_read_num(String id){
        db=getWritableDatabase();
        //获得对象的属性
        Cursor cursor = db.rawQuery("select count(_id) from taskread where r_tid="+id,null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);

        ContentValues values = new ContentValues();
        values.put("t_state",count);//key为字段名，value为值
        db.update(TABLE_NAME2, values, "_id=?", new String[]{id});
        db.close();
    }

    //修改用户密码
    public void updatuserP(String name,String psd){
        //获得对象的属性
        db=getWritableDatabase();
        //获得对象的属性
        ContentValues values = new ContentValues();
        values.put("u_psd",psd);//key为字段名，value为值
        db.update(TABLE_NAME, values, "u_name=?", new String[]{name});
        db.close();
    }

    //修改管理员密码
    public void updatadminP(String name,String psd){
        //获得对象的属性
        db=getWritableDatabase();
        //获得对象的属性
        ContentValues values = new ContentValues();
        values.put("a_psd",psd);//key为字段名，value为值
        db.update(TABLE_NAME11, values, "a_name=?", new String[]{name});
        db.close();
    }

    //修改管理员密码
    public void updatadminP1(String id,String psd){
        //获得对象的属性
        db=getWritableDatabase();
        //获得对象的属性
        ContentValues values = new ContentValues();
        values.put("a_psd",psd);//key为字段名，value为值
        db.update(TABLE_NAME12, values, "_id=?", new String[]{id});
        db.close();
    }

    //修改课程信息
    public void updatCourseInfo(String id,String name,String kk,String man,String info){
        //获得对象的属性
        db=getWritableDatabase();
        //获得对象的属性
        ContentValues values = new ContentValues();
        values.put("c_name", name);
        values.put("c_man", man);
        values.put("c_kk", kk);
        values.put("c_contest", info);
        db.update(TABLE_NAME2, values, "_id=?", new String[]{id});
        db.close();
    }

    //修改用户信息
    public void updatuserP(String id,String psd,String state){
        //获得对象的属性
        db=getWritableDatabase();
        //获得对象的属性
        ContentValues values = new ContentValues();
        values.put("u_psd",psd);//key为字段名，value为值
        values.put("u_state",state);//key为字段名，value为值
        db.update(TABLE_NAME, values, "_id=?", new String[]{id});
        db.close();
    }

    //修改用户头像
    public void updatuserH(String name,String head){
        //获得对象的属性
        db=getWritableDatabase();
        //获得对象的属性
        ContentValues values = new ContentValues();
        values.put("u_head",head);//key为字段名，value为值
        db.update(TABLE_NAME, values, "u_name=?", new String[]{name});
        db.close();
    }

    //修改用户发布的视频评论头像
    public void updatuserVH(String name,String head){
        //获得对象的属性
        db=getWritableDatabase();
        //获得对象的属性
        ContentValues values = new ContentValues();
        values.put("cc_head",head);//key为字段名，value为值
        db.update(TABLE_NAME3, values, "cc_uanme=?", new String[]{name});
        db.close();
    }

    //修改用户发布的动态头像
    public void updatuserCH(String name,String head){
        //获得对象的属性
        db=getWritableDatabase();
        //获得对象的属性
        ContentValues values = new ContentValues();
        values.put("o_head",head);//key为字段名，value为值
        db.update(TABLE_NAME4, values, "o_sendname=?", new String[]{name});
        db.close();
    }

    //修改用户发布的动态评论头像
    public void updatuserCCH(String name,String head){
        //获得对象的属性
        db=getWritableDatabase();
        //获得对象的属性
        ContentValues values = new ContentValues();
        values.put("oc_head",head);//key为字段名，value为值
        db.update(TABLE_NAME5, values, "oc_uname=?", new String[]{name});
        db.close();
    }

    //修改用户数据
    public void updatuserI(String name, String info){
        //获得对象的属性
        db=getWritableDatabase();
        //获得对象的属性
        ContentValues values = new ContentValues();
        values.put("u_info",info);//key为字段名，value为值
        db.update(TABLE_NAME, values, "u_name=?", new String[]{name});
        db.close();
    }

    //根据名字查询用户
    public Cursor queryUser(String name){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME,null,"u_name like ?", new String[] { name+"" },null,null,"_id desc");
        //db.close();
        return cursor;
    }

    //根据名字查询公益组织
    public Cursor queryPubilic(String name){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME12,null,"a_name like ?", new String[] { name+"" },null,null,"_id desc");
        //db.close();
        return cursor;
    }

    //根据教师名字查询话题
    public Cursor queryAdmin(String name){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME11,null,"a_name like ?", new String[] { name+"" },null,null,"_id desc");
        //db.close();
        return cursor;
    }


    //根据用户名查询用户名
    public Cursor queryUserpsd(String name){
        db=getWritableDatabase();

        Cursor psd =db.rawQuery("select u_psd from user where u_name='name'",null);
        return psd;
    }

    //根据id查询数据
    public Cursor queryid(String mykey){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME3,null,"g_name like ?", new String[] { mykey+"" },null,null,"_id desc");
        //db.close();
        return cursor;
    }

    //根据教师名查询任务的数据
    public Cursor querytask_teacher(String name){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME2,null,"t_sendname like ?", new String[] { name+"" },null,null,"_id desc");
        //db.close();
        return cursor;
    }


    //根据话题对应评论数据
    public Cursor querycourse_comment(int id){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME3,null,"cc_vid like ?", new String[] { id+"" },null,null,"_id desc");
        //db.close();
        return cursor;
    }

    //根据id查找获得动态
    public Cursor queryCicle(int id){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME4,null,"_id like ?", new String[] { id+"" },null,null,"_id desc");
        //db.close();
        return cursor;
    }

    //根据id获得对应领养猫咪
    public Cursor queryAdopt(int id){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME3,null,"_id like ?", new String[] { id+"" },null,null,"_id desc");
        //db.close();
        return cursor;
    }

    //根据教师名查询话题的数据
    public Cursor querytopic_teacher(String name){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME5,null,"o_sendname like ?", new String[] { name+"" },null,null,"_id desc");
        //db.close();
        return cursor;
    }

    //根据话题对应评论数据
    public Cursor querycircle_comment(int id){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME5,null,"oc_oid like ?", new String[] { id+"" },null,null,"_id desc");
        //db.close();
        return cursor;
    }

    //查询话题的数据
    public Cursor querytopic(){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME5,null,null,null,null,null,"_id desc");
        //db.close();
        return cursor;
    }

    //查询任务的数据
    public Cursor querytask(){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME2,null,null,null,null,null,"_id desc");
        //db.close();
        return cursor;
    }

    //查询视频的数据
    public Cursor queryAdopt(){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME3,null,null,null,null,null,"_id asc");
        //db.close();
        return cursor;
    }

    //根据课程id查找对应数据
    public Cursor querycourse_col(int id){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME2,null,"_id like ?", new String[] { id+"" },null,null,"_id asc");
        //db.close();
        return cursor;
    }

    //根据文章id查找对应数据
    public Cursor queryarticle_col(int id){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME9,null,"_id like ?", new String[] { id+"" },null,null,"_id asc");
        //db.close();
        return cursor;
    }

    //根据文章id查找对应数据
    public Cursor queryVol_id(int id){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME2,null,"_id like ?", new String[] { id+"" },null,null,"_id asc");
        //db.close();
        return cursor;
    }

    //根据名称搜索对应课程数据
    public Cursor queryvideo_search(String name){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME2,null,"c_name like ?", new String[] { "%"+name+"%" },null,null,"_id asc");
        //db.close();
        return cursor;
    }

    //根据话用户名确定用户收藏数
    public long allmycol(String name,int id){
        db=getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(_id) from mycol where m_uname='"+name+"' and m_vid="+id,null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    //根据话用户名确定用户动态树
    public long allmycircle(String name){
        db=getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(_id) from circle where o_sendname='"+name+"'",null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    //根据用户名确定用户关注数
    public long allmyarticle(String name,int id){
        db=getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(_id) from myarticle where m_uname='"+name+"'and m_vid="+id+"" ,null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    //根据用户名确定用户关注数
    public long allmyarticle(String name){
        db=getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(_id) from myarticle where m_uname='"+name+"'" ,null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    //根据用户名确定用户关注数
    public long allmyapply(String name){
        db=getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(_id) from myapply where m_uname='"+name+"'" ,null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    //根据用户名确定用户申请收养数
    public long allmyapply(String name,int id){
        db=getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(_id) from myapply where m_uname='"+name+"'and m_vid="+id+"" ,null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    //根据话用户名确定用户关注数
    public long allmyvol(String name,int id){
        db=getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(_id) from voluntary where v_uname='"+name+"'and v_rid="+id+"" ,null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    //根据话用户名确定用户关注数
    public long allmyart(String name,int id){
        db=getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(_id) from myarticle where m_uname='"+name+"' and m_vid="+id,null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    //根据话任务点击数确定对应话题和用户总条数
    public long allmycou(String name,int id){
        db=getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(_id) from mycourse where m_uname='"+name+"' and m_vid="+id,null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    //删除收藏数据
    public void delcol(String name,int id){
        db=getWritableDatabase();
        String sql = "delete from "+TABLE_NAME6+" where m_uname='"+name+"' and m_vid="+id;
        db.execSQL(sql);
        db.close();
    }

    //根据id删除用户
    public void deluser(String id){
        db=getWritableDatabase();
        String sql = "delete from "+TABLE_NAME+" where _id="+id;
        db.execSQL(sql);
        db.close();
    }

    //删除管理员
    public void deladmin(String id){
        db=getWritableDatabase();
        String sql = "delete from "+TABLE_NAME12+" where _id="+id;
        db.execSQL(sql);
        db.close();
    }

    //删除加入课程
    public void delcourse(String name,int id){
        db=getWritableDatabase();
        String sql = "delete from "+TABLE_NAME8+" where m_uname='"+name+"' and m_vid="+id;
        db.execSQL(sql);
        db.close();
    }

    //删除课程
    public void delcourse(String id){
        db=getWritableDatabase();
        String sql = "delete from "+TABLE_NAME2+" where _id="+id;
        db.execSQL(sql);
        db.close();
    }

    //删除动态
    public void delcircle(String id){
        db=getWritableDatabase();
        String sql = "delete from "+TABLE_NAME4+" where _id="+id;
        db.execSQL(sql);
        db.close();
    }

    //删除关注数据
    public void delart(String name,int id){
        db=getWritableDatabase();
        String sql = "delete from "+TABLE_NAME10+" where m_uname='"+name+"' and m_vid="+id;
        db.execSQL(sql);
        db.close();
    }

    //查询用户发布的朋友圈
    public Cursor queryCircle_select(){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME4,null,"_id >2",null,null,null,"_id desc");
        //db.close();
        return cursor;
    }

    //查询系统添加的朋友圈
    public Cursor queryCircle_system(){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME4,null,"_id <=2",null,null,null,"_id desc");
        //db.close();
        return cursor;
    }

    //查询用户发布的朋友圈
    public Cursor queryAdopt_select(){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME3,null,"_id >2",null,null,null,"_id desc");
        //db.close();
        return cursor;
    }

    //查询系统添加的朋友圈
    public Cursor queryAdopt_system(){
        db=getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME3,null,"_id <=2",null,null,null,"_id desc");
        //db.close();
        return cursor;
    }

    //根据用户名和朋友圈id查询点赞数目
    public long allcircle_nice(String name,int id){
        db=getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(_id) from comment_nice where r_sname='"+name+"' and r_tid="+id,null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    //查询用户是否已点赞
    public long allcircle_nicenum(String j){
        db=getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(_id) from comment_nice where r_state='"+j+"'",null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    //修改朋友圈点赞数据
    public void updaallcircle_nicenum(String id){
        db=getWritableDatabase();
        //获得对象的属性
        Cursor cursor = db.rawQuery("select count(_id) from comment_nice where r_tid="+id,null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);

        ContentValues values = new ContentValues();
        values.put("t_state",count);//key为字段名，value为值
        db.update(TABLE_NAME4, values, "_id=?", new String[]{id});
        db.close();
    }

    public long allArticleNum( ){
        db=getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(_id) from article",null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    public long allSystemCircle_Num( ){
        db=getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(_id) from circle",null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    public long allAdminNum( ){
        db=getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(_id) from admin",null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    public long allCaseNum( ){
        db=getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(_id) from course",null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }


}

