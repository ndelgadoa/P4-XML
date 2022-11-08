# P4-XML
-README 
URL url = new URL(loc);
                    String path = url.getPath().replace('/', '_');
                    System.out.println(path);
- Hacemos un replace para cambiar las barras '/' por barra bajas '_' ya que en windows no se pueden guardar los archivos con barras. 

                    try (PrintStream docu = new PrintStream("C:\\Users\\nil\\IdeaProjects\\XML\\xml\\" + path + ".txt")) {

                    }
- Aqui crearemos la url en el cual le pondremos de nombre de el path donde guardaremos el archivo                    
 
 
   try {
                                Files.write(Paths.get("C:\\Users\\nil\\IdeaProjects\\XML\\xml\\" + path + ".txt"), loc2.getBytes(), StandardOpenOption.APPEND);
                            } catch (IOException e) {
                            }
                            
- Aqui simplemento haremos el append para que no se sobreescriba, aunque no seria necesario completamente si lo hicieramos fuera del bucle
                            
                            
