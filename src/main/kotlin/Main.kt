package org.example

import java.util.regex.Pattern

/**
 * Написать программу, которая обрабатывает введённые
 * пользователем в консоль команды:
 * exit
 * help
 * add <Имя> phone <Номер телефона>
 * add <Имя> email <Адрес электронной почты>
 *
 * После выполнения команды, кроме команды exit,
 * программа ждёт следующую команду.
 *
 * Имя – любое слово.
 * Если введена команда с номером телефона, нужно проверить,
 * что указанный телефон может начинаться с +, затем идут
 * только цифры. При соответствии введённого номера этому
 * условию – выводим его на экран вместе с именем, используя
 * строковый шаблон. В противном случае - выводим сообщение
 * об ошибке. Для команды с электронной почтой делаем то же
 * самое, но с другим шаблоном – для простоты, адрес должен
 * содержать три последовательности букв, разделённых символами
 * @ и точкой.
 */
fun main() {
    val phonePattern = Pattern.compile("^\\+\\d{12}$")
    val emailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    val namePattern = Pattern.compile("^[a-zA-z]{2,}$")
    val phoneMatcher = phonePattern.matcher("")
    val emailMatcher = emailPattern.matcher("")
    val nameMatcher = namePattern.matcher("")

    println("Основной список команд")
    println("exit - выход из программы")
    println("help - вывести справку")
    println("add <Имя> phone <Номер телефона> - добавить номер телефона для контакта")
    println("add <Имя> email <Адрес электронной почты> - добавить адрес электронной почты для контакта")
    print("Введите команду: ")
    while (true) {
        val command = readln().split(" ");
        if (command[0] == "exit") {
            break
        } else if (command[0] == "help") {
            println("exit - выход из программы")
            println("help - вывести справку")
            println("add <Имя> phone <Номер телефона> - добавить номер телефона для контакта")
            println("add <Имя> email <Адрес электронной почты> - добавить адрес электронной почты для контакта")
        } else if (command[0] == "add" && command.size == 4) {
            val name = command[1]
            val type = command[2]
            val value = command[3]
            nameMatcher.reset(name)
            if (nameMatcher.matches()) {
                if (type == "phone") {
                    phoneMatcher.reset(value)
                    if (phoneMatcher.matches()) {
                        println("$name: $value")
                    } else {
                        println("Ошибка: неверный номер телефона $value")
                    }
                } else if (type == "email") {
                    emailMatcher.reset(value)
                    if (emailMatcher.matches()) {
                        println("$name: $value")
                    } else {
                        println("Ошибка: неверный адрес электронной почты $value")
                    }
                } else {
                    println("Ошибка: неверный тип контакта")
                }
            }
            else {
                println("Ошибка: неверно введено имя. Имя должно содержать только буквы!")
            }
        } else {
            println("Ошибка: неверно введена команда добавления контакта.")
            println("Введите: add <Имя> тип контакта (phone, email) и контакт (+996111222333444, mm@qq.rr).")
        }
    }
}


