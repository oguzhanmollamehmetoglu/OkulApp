# Okul Yönetim Sistemi
## Proje Özeti
Bu proje, eğitim kurumlarında öğrenci ve bölüm yönetimini kolaylaştırmak amacıyla geliştirilmiş bir Okul Yönetim Sistemidir. Sistem, Java programlama dili ile geliştirilmiş olup, arka planda MySQL veritabanı kullanarak verilerin kalıcı olarak saklanmasını sağlar. Projede öğrenci ve bölüm ekleme, silme, güncelleme gibi işlemler yapılabilir ve bu işlemler kullanıcı dostu bir arayüz üzerinden kolayca gerçekleştirilebilir. Proje, kullanıcıların öğrenci ve bölümler üzerinde hızlı aramalar yaparak yönetimi daha etkili hale getirmesini sağlar.

## Teknolojiler
### Programlama Dili:
<ul>
  <li>Java</li>
</ul>

### Veritabanı:
<ul>
  <li>MySQL</li>
</ul>

### Geliştirme Ortamı: 
<ul>
  <li>Eclipse IDE</li>
</ul>

### Arayüz: 
<ul>
  <li>Java Swing</li>
</ul>

## Proje Özellikleri
### Bölüm Yönetimi:
Bölüm Ekleme, Güncelleme ve Silme: Kullanıcılar, yeni bölümler ekleyebilir, mevcut bölümleri güncelleyebilir veya silebilir. Bu işlemler, MySQL veritabanı ile senkronize bir şekilde gerçekleştirilir. Her bölüm bir ID ile tanımlanır ve sistemde listelenir.
#### Veri Doğrulama: 
Bölüm ekleme ve güncelleme işlemlerinde kullanıcıdan alınan veriler, geçerlilik kontrolüne tabi tutulur. Yanlış veya eksik veri girişlerinde kullanıcıya bilgilendirme mesajları sunulur.

### Öğrenci Yönetimi:
Öğrenci Ekleme, Güncelleme ve Silme: Kullanıcılar, öğrenci bilgilerini (ad, soyad, bölüm) ekleyebilir, mevcut öğrenci bilgilerini güncelleyebilir veya öğrenci kayıtlarını silebilir. Öğrenci bölümleri, mevcut bölümlere göre atanır ve her öğrenciye bir bölüm atanır.

#### Arama Özelliği: 
Öğrenciler, isim veya diğer kriterlere göre aranabilir. Bu özellik, kullanıcıların öğrenciler arasında hızlıca arama yapmasını sağlar.

#### Bölüm Atama: 
Öğrencilere bir bölüm atanarak, hangi bölümde okudukları sistemde saklanır. Bölüm atama ve güncelleme işlemleri de MySQL veritabanı üzerinden yapılır.

## Veritabanı Yapısı
### Bölümler Tablosu:
<ul>
  <li>id (Primary Key)</li>
  <li>bolum_adı (String)</li>
</ul>

### Öğrenciler Tablosu:
<ul>
  <li>id (Primary Key)</li>
  <li>ogrenci_adı (String)</li>
  <li>ogrenci_soyadı (String)</li>
  <li>bolum_id (Foreign Key)</li>
</ul>

## Kullanıcı Arayüzü
Uygulama arayüzü Java Swing ile geliştirilmiştir ve bölümler ile öğrenciler için ayrı sekmeler bulunmaktadır. Kullanıcılar, her sekmede ilgili işlemleri (ekleme, güncelleme, silme) kolayca yapabilir ve sonuçları anında tablolar üzerinde görebilirler.

Bu proje, okul yönetim süreçlerini kolaylaştırmak için geliştirilmiş olup, öğrencilerin ve bölümlerin etkili bir şekilde yönetilmesini sağlar.

<img src="https://github.com/oguzhanmollamehmetoglu/OkulApp/blob/master/OkulApp%20Foto%C4%9Fraflar/panel1.png"/>
<img src="https://github.com/oguzhanmollamehmetoglu/OkulApp/blob/master/OkulApp%20Foto%C4%9Fraflar/panel2.png"/>
