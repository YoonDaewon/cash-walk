var express = require('express');
var router = express.Router();
var async = require('async');
 
var MongoClient = require('mongodb').MongoClient;
var assert = require('assert');
var url = 'mongodb://localhost:27017/test';
 
// 전체 리스트 조회
 
router.route('/')
 
.post(function(req, res, next){
    
    var userId = req.body.id;
    var userName = req.body.name;
    var userAge = req.body.age;
    var userGender = req.body.gender;
    
    MongoClient.connect(url, function(err, db){
        if(err){
            console.log("Unable to connect to the mongoDB server. Error:", err);
        }else{
            console.log("Connection established to", url);
            var collection = db.collection('person');
            var doc = {
                    "id":userId,
                    "name":userName,
                    "age":userAge,
                    "gender":userGender
            };
            
            collection.insert(doc, function(err){
                if(err){
                    console.log("Insert fail");
                }else{
                    console.log("Insert success");
                    res.json({"id":userId});
                }
            });    
        }
    });
    
})
 
module.exports = router;