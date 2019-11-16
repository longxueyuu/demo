package main

import (
	"context"
	"fmt"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/bson/primitive"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
	"time"
)

type UserRelation struct {
	ID      primitive.ObjectID `bson:"_id,omitempty" json:"id"`
	RID     string             `bson:"rid" json:"rid"`
	Match   *Match             `bson:"match" json:"match"`
	Comment *Comment           `bson:"comment" json:"comment"`
	Gift    *Gift              `bson:"gift" json:"gift"`
}

type Match struct {
	MatchID int64     `bson:"match_id",json:"match_id"`
	MTime   time.Time `bson:"mtime" json:"mtime"`
}

type Comment struct {
	CommentID int64     `bson:"comment_id",json:"comment_id"`
	MTime     time.Time `bson:"mtime" json:"mtime"`
}

type Gift struct {
	Gift  int64     `bson:"gift_id",json:"gift_id"`
	MTime time.Time `bson:"mtime" json:"mtime"`
}

func main() {
	fmt.Println("hello mongo")

	mongoUrl := "mongodb://localhost:27017"
	opts := options.Client().ApplyURI(mongoUrl)

	mongoClient, err := mongo.Connect(context.Background(), opts)
	if err != nil {
		fmt.Println(err)
	}

	dbNames, err := mongoClient.ListDatabaseNames(context.Background(), bson.D{})
	if err != nil {

	}

	fmt.Println(dbNames)
	db := mongoClient.Database("poke_kara")
	userRelationCol := db.Collection("user_relation")

	var userRelation = &UserRelation{}
	userRelation.RID = "user_relation_u01_u02"
	userRelation.Match = &Match{MatchID: 100}
	userRelation.Comment = &Comment{CommentID: 100}

	upsert := true
	_, err = userRelationCol.UpdateOne(
		context.Background(),
		bson.M{"rid": "user_relation_u01_u02"},
		bson.M{"$set": bson.M{"match.mtime": time.Now(),
			"gift":    bson.M{"gift_id": 102, "mtime": time.Now()},
			"comment": bson.M{"comment_id": 102, "mtime": time.Now()},
		},
		},
		&options.UpdateOptions{Upsert: &upsert},
	)
	if err != nil {
		fmt.Println("update err: " + err.Error())
	}

	collectionNames, err := db.ListCollectionNames(context.Background(), bson.D{})

	fmt.Println(collectionNames)

	res := userRelationCol.FindOne(context.Background(), bson.M{"rid": "user_relation_u01_u02"})

	var result UserRelation
	err = res.Decode(&result)
	if err != nil {
		fmt.Println("decode err: " + err.Error())
	}
	fmt.Println(result)
	fmt.Println(result.Match)
	fmt.Println(result.Gift)

	relation := make(map[string]int)
	fmt.Println(relation["a"], relation[""])

	ts, err := time.Parse("2006-01-02 15:04:05", "51702-05-21 22:36:18")
	if err != nil {

	}

	go func() {
		f()
		fmt.Println("Returned normally from f.")
	}()

	time.Sleep(30 * time.Second)

	ts = time.Now()
	fmt.Println(ts.Unix())

	tsInMillis := time.Unix(1569402480978/1000, 0)
	fmt.Println(tsInMillis)

	str := getTimeOfNDaysAgo(3).Format("2006-01-02 00:00:00")
	fmt.Println(str)
}

func getTimeOfNDaysAgo(n int) time.Time {
	return time.Now().AddDate(0, 0, -n)
}

func f() {
	//defer func() {
	//	if r := recover(); r != nil {
	//		fmt.Println("Recovered in f", r)
	//	}
	//}()
	fmt.Println("Calling g.")
	g(0)
	fmt.Println("Returned normally from g.")
}

func g(i int) {
	if i > 3 {
		fmt.Println("Panicking!")
		panic(fmt.Sprintf("%v", i))
	}
	defer fmt.Println("Defer in g", i)
	fmt.Println("Printing in g", i)
	g(i + 1)
}
