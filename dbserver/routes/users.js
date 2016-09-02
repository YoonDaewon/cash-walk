var express = require('express');
var router = express.Router();
var async = require('async');
 
var MongoClient = require('mongodb').MongoClient;
var assert = require('assert');
var url = 'mongodb://localhost:27017/cashwalk';
 
// 전체 리스트 조회
 
router.route('/')
 
.post(function(req, res, next){
    
    var userId = req.body.id;
    var userPw = req.body.pw;
    var Record = req.body.record;
    var usercash = req.body.cash;
    
    MongoClient.connect(url, function(err, db){
        if(err){
            console.log("Unable to connect to the mongoDB server. Error:", err);
        }else{
            console.log("Connection established to", url);
            var collection = db.collection('CW_DATA');
            var doc = {
                    "id":userId,
                    "pw":userPw,
                    "recored":Record,
                    "cash":usercash
            };
            
            collection.insert(doc, function(err){
                if(err){
                    console.log("Insert fail");
                }else{
                    console.log("Insert success");
                    res.json({"id":userId,"pw":userPw});
                }
            });    
        }
    });
    
})
 
module.exports = router;

