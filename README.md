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
### バリデーション = 入力チェックのこと  

## gradleの場合
```
dependencies {
  ...
  implementation 'org.springframework.boot:spring-boot-starter-validation'
} 
```  
を依存関係に追加。




