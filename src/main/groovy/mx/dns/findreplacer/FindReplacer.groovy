package mx.dns.findreplacer

class FindReplacer {

  static void findReplace(String path, String find, String replace) {

    def file = new File(path);
    if (!file.exists()) {
      throw new IllegalArgumentException("The provided path[" + path  + "] does not exist.")
    }

    new FindReplacer().findReplace(file, find, replace)
  }



  def findReplace(File file, String find, String replace) {
    if (file.isDirectory()) {
      file.listFiles().each { f -> findReplace(f, find, replace) }
    }

    
    def ant = new AntBuilder()
    ant.replace(file: file.absolutePath, token: find, value: replace)
  }
}
