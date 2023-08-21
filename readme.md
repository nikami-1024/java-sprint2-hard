# Sales manager
## Project from hard webinar - sprint #2

### Функционал программы:

Обработка значений и вывод статистики на основе данных по продажам и доставкам из файлов CSV двух разных форматов.

- Считывание, парсинг и сохранение значений из файла продаж, поиск самого популярного проданного товара.

- Считывание, парсинг и сохранение значений из файлов доставок для нескольких городов, поиск самого популярного города, куда доставлялись товары. 

- Сверка отчётов продаж и доставок, вывод обнаруженных несовпадений и статуса сверки.

Пути к файлам данных, их имена и количество городов доставки настраиваются в файле *Main.java*.

### Форматы файлов:

#### Отчёт по продажам

Файл *resources/sales.csv*:

| title   | count | price | city | received |
|---------|-------|-------|------|----------|
| морковь | 3     | 7     | spb  | true     |
| тыква   | 1     | 24    | msc  | false    |

```csv
title,count,price,city,received
морковь,3,7,spb,true
тыква,1,24,msc,false
```

#### Отчёт по доставкам

Файлы формата *resources/city.delivery.csv*:

| year | month | day | title   | count |
|------|-------|-----|---------|-------|
| 2021 | 03    | 13  | морковь | 3     |
| 2022 | 04    | 16  | тыква   | 2     |

```csv
year,month,day,title,count
2021,03,13,морковь,3
2022,04,16,тыква,2
```