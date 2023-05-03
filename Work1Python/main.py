
import datetime

def Menu_Notes():

    while True:
        with open("Notes.csv", mode = "r", encoding = "utf-8") as file_read:
                data = []
                for line in file_read:
                    tmp = line.strip().split(';')
                    data.append(tmp)

        print('Выберите действие: ')
        print('1 - Добавить заметку')
        print('2 - Прочитать заметки')
        print('3 - Редоктировать заметку')
        print('4 - Удалить заметку')
        print('0 - Завершить программу')
        get = input('Введите действие: ')
        if get == '0':
            print('До свидания!')
            break
        elif get == '1':
            SavingNote(AddNotes(data))
        elif get == '2':
            ReadNotes(data)
        elif get == '3':
            SavingNote(EditNotes(data))
        elif get == '4':
            SavingNote(DeleteNote(data))
        else:
            print('Некорректный ввод данных, введите ещё раз: ')

def SavingNote(data : dict) -> None: # Сохранение заметки
    data2 = []
    lst = []
    for values in data:
        data2.append(values)
        data2.append("\n")
    for i in data2:
        lst.append(";".join(i))

    with open("Notes.csv", mode= 'w', encoding='utf-8') as file_write:
         for el in lst:
              file_write.writelines(el)

def AddNotes(data : dict) -> dict: # Добавить заметки

    for el in range(len(data)):
        id_note = el + 1

    data1 = []
    for el in data:
        data1.append(el)
    
    header = input('Введите заголовок: ')+':'
    note_body = input('Введите заметку: ')
    dt = datetime.datetime.now()
    date_time = 'Дата создания заметки: ' + dt.strftime("%Y-%m-%d %H:%M:%S")
    id_note = '№' + str(id_note + 1)
    data_recording = [[id_note, header, note_body, date_time]]
    data = data1 + data_recording
    return data
    

def ReadNotes(data : dict) -> None: # Прочитать заметки
     for el in data:
        for idx in range(len(el)):
            print(el[idx])

def EditNotes(data : dict) -> dict: # Редактировать заметки
    id_note = input("Введите номер заметки: ")
    int_id_note = int(id_note)
    get_note = input("Выберети что хотите редактировать: \n"
                "1 - Загаловок\n"
                "2 - Запись в заметке\n"
                "Введите действие: ")
    dt = datetime.datetime.now()
    date_time = 'Дата создания заметки: ' + dt.strftime("%Y-%m-%d %H:%M:%S")
    if int(get_note) == 1:
        data[int_id_note - 1][1] = input("Введите заголовок: ") + ": "
        data[int_id_note - 1][3] = date_time
    if int(get_note) == 2:
        data[int_id_note - 1][2] = input("Введите новую запись в заметке: ")
        data[int_id_note - 1][3] = date_time
    return data

def DeleteNote(data : dict) -> dict: # Удалить заметку
    id_note = input("Введите номер заметки: ")
    int_id_note = int(id_note)
    count = 0
    data1 = []
    for idx in range(len(data)):
        if idx == int_id_note - 1:
            for el in data:
                data1.append(el)
            data1.pop(int_id_note - 1)

    data2 = []
    for el in data1:
        count += 1
        el[0] = '№' + str(count)
        data2.append(el)
    return data2       
     
Menu_Notes()