var express = require('express');
var router = express.Router();
var async = require('async');
 
var MongoClient = require('mongodb').MongoClient;
var assert = require('assert');
var url = 'mongodb://localhost:27017/cashwalk';
 
//전체 리스트 조회

router.route('/')	// 해당 코드에 대한 접근 경로 설정, 프로그램으로 접근하면 아래있는 코드들 수행
 
.get(function(req, res, next){	// 조회, 전체조회, 등의 기능들이 들어가 있음
    
    var userName = req.query.name;
    var minAge = req.query.minAge;
    var maxAge = req.query.maxAge;
    
    if(userName == null && minAge == null && maxAge == null){	// 전체 조회
        
    
    
    MongoClient.connect(url, function(err, db){
        if(err){
            console.log("Unable to connect to the mongoDB server. Error:", err);
        }else{
            console.log("Connection established to", url);
            var collection = db.collection('CW_DATA');
            
            // _id 제거
            collection.find({}).toArray(function(err, docs){	// array 메서드로 DB데이타 다 긁음
                db.close();	// DB pool을 관리 
                async.each(docs, function(doc, cb){	// mongodb는 _id를 가져오기때문에 제거
                    
                    delete doc._id;
                    return cb(null);
                    
                }, function(err){
                    if(err){
                        console.log("async.each Error");
                    }else{
                        
                    }
                })
                
                res.json(docs);
            });
        }
    });
    
    }else if(userName == null && minAge != null && maxAge != null){    //최소 나이 최대 나이 검색
        
        
        MongoClient.connect(url, function(err, db){
            if(err){
                console.log("Unable to connect to the mongoDB server. Error:", err);
            }else{
                console.log("Connection established to", url);
                var collection = db.collection('CW_DATA');
                
                collection.find({age: {$gt: minAge, $lt: maxAge}}).toArray(function(err, docs){
                    db.close();
                    async.each(docs, function(doc, cb){
                        
                        delete doc._id;
                        return cb(null);
                        
                    }, function(err){
                        if(err){
                            console.log("async.each Error");
                        }else{
                            
                        }
                    })
                    
                    res.json(docs);	// josn으로 클라에게 응답을 줌
                });
            }
        });
    }else if(userName != null && minAge == null && maxAge == null){ //이름으로 조회
        
        MongoClient.connect(url, function(err, db){
            if(err){
                console.log("Unable to connect to the mongoDB server. Error:", err);
            }else{
                console.log("Connection established to", url);
                var collection = db.collection('person');
                
                // /userName/
                collection.find({name: new RegExp(userName)}).toArray(function(err, docs){
                    db.close();
                    async.each(docs, function(doc, cb){
                        
                        delete doc._id;
                        return cb(null);
                        
                    }, function(err){
                        if(err){
                            console.log("async.each Error");
                        }else{
                            
                        }
                    })
                    
                    res.json(docs);
                });
            }
        });
    }
    
})
 
 
.post(function(req, res, next){
    
    var userId = req.body.id;
    var userPw = req.body.pw;
    var userRecord = req.body.record;
    var userCash = req.body.cash;
    
    MongoClient.connect(url, function(err, db){
        if(err){
            console.log("Unable to connect to the mongoDB server. Error:", err);
        }else{
            console.log("Connection established to", url);
            var collection = db.collection('CW_DATA');
            var doc = {
                    "id":userId,
                    "pw":userPw,
                    "record":userRecord,
                    "cash":userCash
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
 
router.route('/:userId')	// 아이디로 관리하기 위한
.get(function(req, res, next){
    
    var userId = req.params.userId;
    
    MongoClient.connect(url, function(err, db){
        if(err){
            console.log("Unable to connect to the mongoDB server. Error:", err);
        }else{
            console.log("Connection established to", url);
            var collection = db.collection('CW_DATA');
            collection.findOne({id:userId}, function(err, doc){	// 아이디로 특정 데이터 조회
                db.close();
                if(err){
                    console.log("findPersonById Error :", err);
                }else{
                    console.log(doc);
                    delete doc._id;
                    res.json(doc);
                }
            });
        }
    });
    
})
 
.delete(function(req, res, next){
    
    var userId = req.params.userId;
    
    MongoClient.connect(url, function(err, db){
        if(err){
            console.log("Unable to connect to the mongoDB server. Error:", err);
        }else{
            console.log("Connection established to", url);
            var collection = db.collection('person');
            
            collection.remove({id:userId}, function(err){
                if(err){
                    console.log("Delete fail");
                }else{
                    console.log("Delete success");
                    res.json({"id":userId});
                }
            });
    
        }
    });
    
})
 
//사용자 업데이트
.put(function(req, res, next){
    
    var originalId = req.params.userId;
    
    var userId = req.body.id;
    var userPw = req.body.pw;
    var userRecord = req.body.record;
    var userCash = req.body.cash;
    
    MongoClient.connect(url, function(err, db){
        if(err){
            console.log("Unable to connect to the mongoDB server. Error:", err);
        }else{
            console.log("Connection established to", url);
            var collection = db.collection('person');
            var doc = {
            		"id":userId,
                    "pw":userPw,
                    "record":userRecord,
                    "cash":userCash
            };
            
            collection.update({id:originalId},{$set:doc},function(err){
                if(err){
                    console.log("Update fail");
                }else{
                    console.log("Update success");
                    res.json({id:originalId});
                }
            });
 
        }
    });
    
})
 
module.exports = router;
