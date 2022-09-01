
# RaiseTech_subject7-b
=======
# 第7回追加課題　REST API  
## 名前以外にも生年月日を受け取れるようにする  
### やったこと  
### ①前回課題「クエリ文字列を受け取れるようにする」の複数バージョンを作成    
1. Controllerクラスをつくり@RestControllerを付与  
2. ("/names)に対応するGETリクエスト作成(メソッドに@GetMapping付与）
3. メソッドの引数に@RequestParamでname属性を指定(,で区切り２つ指定する)  
4. 戻り値として name: と DOB(day of birth): を返す。  
<br>　
### ②GitBash、Postmanでリクエストが出来ているか確認  
## GitBash  
<img width="457" alt="スクリーンショット 2022-08-31 174221" src="https://user-images.githubusercontent.com/107123973/187672304-82843fb1-817e-4b01-af37-d77951b889fd.png">  

## name:aki DOB:1984/04/13を確認  
<br>  

## Postman  
<img width="641" alt="スクリーンショット 2022-08-31 174405" src="https://user-images.githubusercontent.com/107123973/187672384-3fe8d555-93b5-4281-af33-d8e42f7280e7.png">  

## 200番(リクエスト成功) name:aki DOB:1984/04/13を確認  
<br>

## バリデーションについて調べ名前が空文字、null、20文字以上以上の場合はエラーとする  
(バリデーション = 入力チェックのこと)  

### やったこと  
### build.gradleに依存関係を追加
```
dependencies {
  ...
  implementation 'org.springframework.boot:spring-boot-starter-validation'
} 
```    
これでValidationに関するアノテーションが使えるようになる。  

- その後の流れ  
1.任意のPOSTリクエストを作成（今回は名前を登録するPOSTリクエストをコピペ使用）  
2.CreateFormクラスのバリデーションしたいフィールドにアノテーションを追加  
3.Controllerクラスに@Validated、メソッドの引数であるCreateFormの前に@Validを付与、  
~~@Validを付与した引数のすぐ後ろに**BindingResult**を書く。~~  

<br>  

~~### バリデーション結果を格納：@BindingResult    
@BindingResultはメソッド引数として直前のフォームオブジェクトのバリデーション結果を格納する。  
@BindingResultはメソッドの引数の並び順をバリデーション対象の直後にすることが必須なので注意。  
bindeingresultに入力チェックの結果が格納される。hasErrors()メソッドは入力チェックに引っかかったかどうかの確認を行うメソッドであり、入力チェックに引っかかったら元のページを表示し、そうでない場合は結果表示を行う。~~

```
@PostMapping("/names")
    public ResponseEntity<String> create(@RequestBody @Valid CreateForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return;
        }
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080")
                .path("/names/id")
                .build()
                .toUri();
        return ResponseEntity.created(url).body("name successfully created");
    }
}
```
# 上記削除しコードを変更  
参考にしたサイト:SpringBootで作成したREST APIのリクエストに対するバリデーションアノテーションの付け方 https://qiita.com/MrMs/items/19ea03b069c226c323bc  
```
 @PostMapping("/names")
    public ResponseEntity<String> create(@RequestBody @Valid CreateForm form) {
        URI url = UriComponentsBuilder.fromUriString("http://localhost:8080")
                .path("/names/id")
                .build()
                .toUri();
        return ResponseEntity.created(url).body("name successfully created");
    }
}
```
- BindingResultを消す  
ResponseEntityの戻り値がないため、BindingResultのifでうまくキャッチできなかった。
これでも動作しました。  
### 実行結果   
正常に動作（POSTリクエスト成功：201　name successfully createdが返ってくる)  
<img width="453" alt="スクリーンショット 2022-09-01 102619" src="https://user-images.githubusercontent.com/107123973/187815836-616c9d87-25b1-497f-92a6-6ad8a4235e3f.png">
<img width="668" alt="スクリーンショット 2022-09-01 103214" src="https://user-images.githubusercontent.com/107123973/187815869-a7f09762-c190-4357-b422-2c340cb5fe31.png">
   
### Vaidation
1.nullの場合  
<img width="449" alt="スクリーンショット 2022-09-01 103036" src="https://user-images.githubusercontent.com/107123973/187814440-cfab2ff7-0d7c-4057-81d2-109ad8569c13.png">
<img width="653" alt="スクリーンショット 2022-09-01 103527" src="https://user-images.githubusercontent.com/107123973/187814468-12eb0b79-b350-46fe-9ce4-5549a13fc05a.png">    
2.空文字の場合  
<img width="455" alt="スクリーンショット 2022-09-01 102806" src="https://user-images.githubusercontent.com/107123973/187815544-360b847f-ecf0-438d-aa42-8f1d5a3204df.png">
<img width="659" alt="スクリーンショット 2022-09-01 103410" src="https://user-images.githubusercontent.com/107123973/187815426-ab95418a-ff9d-48f7-94de-f6aa200e2bf3.png">


3.20文字以上の場合  
<img width="448" alt="スクリーンショット 2022-09-01 102721" src="https://user-images.githubusercontent.com/107123973/187814878-ece0495f-bc2c-4cf3-9e95-a5f6da5a3a14.png">
<img width="656" alt="スクリーンショット 2022-09-01 103458" src="https://user-images.githubusercontent.com/107123973/187814940-c08ec4ab-d9c2-4367-9bd5-7a97f99fd68c.png">

|アノテーション|内容|  
|:---:|:---:|  
|@NotNull|nullでない。（空文字列やスペース、タブのみは許可する）|  
|@NotEmpty|空でない。（nullと空文字列は不許可、スペースやタブのみのみは許可）|  
|@NotBlank|空白でない。（null、空文字列、スペースやタブのみだと不許可）|  
|@Size(min, max)	|要素数の範囲を指定。Stringなら文字数、ListやMapなら要素数。|  
|@Max, @Min|数値の上限と下限を指定。|  
|@Email|メールアドレスとして妥当かチェックする。|  
|@Pattern(regex)	|指定した正規表現に一致するかチェックする|  
|@AssertTrue|Trueであることをチェックする。|   


参考にしたサイト：https://www.tsuchiya.blog/spring-boot-step4/  
アノテーションさらに詳細 → https://penguinlabo.hatenablog.com/entry/springframework/validator





>>>>>>> 7ed8ba5a38dcf55e170d155a24fe363e030cdec7
