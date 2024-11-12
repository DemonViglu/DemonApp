import java.io.File
class FileManager {

    fun SaveFileWithPath(path:String,content:String){
        File(path).writeText(content);
    }

    fun ReadFileWithPath(path:String):String{
        return File(path).readText();
    }

    fun FileWithPathExist(path:String):Boolean{
        return File(path).exists();
    }
}