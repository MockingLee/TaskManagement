# TaskManagement

## ER Relationship

![ER RelationShip](https://github.com/MockingLee/TaskManagement/raw/master/ReadMeResource/1256484947.jpg "ER RelationShip")

## 系统架构图

![structure](https://github.com/MockingLee/TaskManagement/raw/master/ReadMeResource/架构图.png "structure")

## API 设计
`/api/login`

Field | Description
------|------------
**username** | 用户名
**password** | 密码

```javascript
// 发送格式
{
  'username' : 123 , 
  'password' : 123 , 
}
// 返回格式

{
  'success' : True , 
  'info' : { uid : 100007, username : "dzb", password : "123", weight : 0}
}
```

`/api/addAccount`

Field | Description
------|------------
**info** | 当前登录用户信息，即登录时接受的info字段
**msg** | 需要添加的用户信息 ：用户名  权限（可选，默认为0）
**res** | （可选）添加失败的原因 double/weight

```javascript
// 发送格式
{
  'info' : { uid : 100007, username : "dzb", password : "123", weight : 0} , 
  'msg' : {username : 123 , weight : 0}
}
// 返回格式
// success
{
  'success' : True , 
  'msg' : {uid:100008, username: "123", password : "n1as9jx", weight : 0}
}
// fail
{
  'success' : False , 
  'res' : "double"
}
```

`/api/delAccount`

Field | Description
------|------------
**info** | 当前登录用户信息，即登录时接受的info字段
**msg** | 需要删除的用户信息 ：uid
**res** | （可选）删除失败的原因 none/weight
```javascript
// 发送格式
{
  'info' : { uid : 100007, username : "dzb", password : "123", weight : 0} ,
  'msg' : { uid : 100010}
}
// 返回格式
{
  'success' : True , 
  'res' : "none"
}
```

`/api/changePass`

Field | Description
------|------------
**info** | 当前登录用户信息，即登录时接受的info字段
**uid** | 需要更改的用户uid
**msg** | 新密码信息
**res** | （可选）删除失败的原因 notfound/weight
**islogout** | 是否logout 
```javascript
// 发送格式
{
  'info' : {uid : "" , username : "dzb", password : "123", weight : 0} ,
  'uid' : 100007 ,
  'msg' : { password : "1234"}
}
// 返回格式
//success
{
  'success' : True , 
  'islogout' : True
}
//fail
{
  'success' : False ,
  'res' : 'weight'
}
```

`/api/changeInfo`

Field | Description
------|------------
**info** | 当前登录用户信息，即登录时接受的info字段
**uid** | 需要更改的用户uid
**msg** | 需要修改或添加的信息
**res** | （可选）删除失败的原因 notfound/weight
```javascript
// 发送格式
{
  'info' : {uid : "" , username : "dzb", password : "123", weight : 0} ,
  'uid' : 100007 ,
  'msg' : {name : "wyx" , school : "" , profession : "" , phone "" ,sex : "", birth : ""}
}
// 返回格式
//success
{
  'success' : True
}
//fail
{
  'success' : False ,
  'res' : 'weight'
}
```
`/api/addTask`

Field | Description
------|------------
**info** | 当前登录用户信息，即登录时接受的info字段
**msg** | 添加的任务信息
**res** | （可选）添加失败的原因 error
```javascript
// 发送格式
{
  'info' : {uid : "" , username : "dzb", password : "123", weight : 0} ,
  'uid' : 100007 ,
  'msg' : {  title : "", content : ""}
}
// 返回格式
//success
{
  'success' : True
}
//fail
{
  'success' : False ,
  'msg' : {tid : "" , title : "", content : "", init_time, update_time, process}
  'res' : 'error'
}
```
`/api/updateTask` 

Field | Description
------|------------
**info** | 当前登录用户信息，即登录时接受的info字段
**tid** | tid
**msg** | 更新的任务信息
**res** | （可选）更新失败的原因 error
```javascript
// 发送格式
{
  'info' : {uid : "" , username : "dzb", password : "123", weight : 0} ,
  'tid' : "" ,
  'msg' : {  process : ""}
}
// 返回格式
//success
{
  'success' : True
}
//fail
{
  'success' : False ,
  'res' : 'error'
}
```

`/api/changeTask`

Field | Description
------|------------
**info** | 当前登录用户信息，即登录时接受的info字段
**tid** | tid
**msg** | 修改的任务信息
**res** | （可选）修改失败的原因 error
```javascript
// 发送格式
{
  'info' : {uid : "" , username : "dzb", password : "123", weight : 0} ,
  'tid' : "" ,
  'msg' : { title , content}
}
// 返回格式
//success
{
  'success' : True
}
//fail
{
  'success' : False ,
  'res' : 'error'
}
```
`/api/delTask`

Field | Description
------|------------
**info** | 当前登录用户信息，即登录时接受的info字段
**tid** | tid
**res** | （可选）修改失败的原因 error
```javascript
// 发送格式
{
  'info' : {uid : "" , username : "dzb", password : "123", weight : 0} ,
  'tid' : "" 
}
// 返回格式
//success
{
  'success' : True
}
//fail
{
  'success' : False ,
  'res' : 'error'
}
```

`/api/getTask`

根据tid查找单条任务

Field | Description
------|------------
**info** | 当前登录用户信息，即登录时接受的info字段
**tid** | tid
**msg** | 返回当前查询的任务信息
**res** | （可选）修改失败的原因 weight/notfound
```javascript
// 发送格式
{
  'info' : {uid : "" , username : "dzb", password : "123", weight : 0} ,
  'tid' : "" 
}
// 返回格式
//success
{
  'success' : True ，
  'msg' : {tid , title , content , init_time , update_time , process}
}
//fail
{
  'success' : False ,
  'res' : 'weight'
}
```

`/api/getUserAllTask`

根据uid查找当前用户所有任务

Field | Description
------|------------
**info** | 当前登录用户信息，即登录时接受的info字段
**msg** | 返回当前查询的任务信息
**res** | （可选）修改失败的原因 weight
```javascript
// 发送格式
{
  'info' : {uid : "100000" , username : "dzb", password : "123", weight : 0} 
}
// 返回格式
//success
{
  'success' : True ，
  'msg' :[ 
    {tid , title , content , init_time , update_time , process},
    {tid , title , content , init_time , update_time , process},
    ...
  ]
}
//fail
{
  'success' : False ,
  'res' : 'weight'
}
```

`/api/getUsers`

查找所有用户信息

Field | Description
------|------------
**info** | 当前登录用户信息，即登录时接受的info字段
**msg** | 返回当前查询的任务信息
**res** | （可选）修改失败的原因 weight
```javascript
// 发送格式
{
  'info' : {uid : "100000" , username : "dzb", password : "123", weight : 0} 
}
// 返回格式
//success
{
  'success' : True ，
  'msg' :[ 
    {name, school, profession, phone, sex, birth},
    {name, school, profession, phone, sex, birth},
    ...
  ]
}
//fail
{
  'success' : False ,
  'res' : 'weight'
}
```