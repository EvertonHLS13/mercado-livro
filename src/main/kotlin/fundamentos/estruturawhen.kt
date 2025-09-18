package fundamentos

fun main(){

//    val x = 25

//    when(x) {
//        5, -5 -> println("x == 5")
//        8 -> println("x == 8")
//        9 -> println("x == 9")
//        10 ->{
//            println("x == 10")
//            println("x é uma dezena")
//        }
//        in 11..15 -> println("x está entre 11 e 15")
//        !in 16 ..20 -> println("Não está no range de 16 a 20")
//        else -> println("Numero não mapeado")
//    }

      when{
          comecacomOi(5) -> println("5")
          //comecacomOi("Oi, tudo bem?") -> println("Oi, tudo bem?")
          comecacomOi("Oi, estou bem!") -> println("Oi, estou bem!")
      }

}

fun comecacomOi(x: Any): Boolean {
    return when(x){
        is String -> x.startsWith("Oi")
        else -> false
    }
}