package fundamentos

fun main(){
//   resultadoDaNota(6)
//   resultadoDaNota(5)
//   resultadoDaNota(3)

    parOuImpar(2)
    parOuImpar( 9)
}

fun parOuImpar(numero: Int){
    return if(numero % 2 == 0){
        println("Par")
    } else {
        println("Impar")
    }

}

//Se a nota >= 6 -> passou
//Se a nota for > 4 -> recuperação
//Se a nota fora < 4 -> reprovou
fun resultadoDaNota(nota: Int) {
    if (nota >=6){
        println("Passou!")
    } else if (nota >= 4){
        println("Recuperação")
    }else{
        println("Reprovou!")
    }
}