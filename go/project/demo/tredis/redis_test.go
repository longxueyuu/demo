package tredis

import (
	"fmt"
	"github.com/go-redis/redis"
	"log"
	"testing"
	"time"
)

var (
	client = redis.NewClient(
		&redis.Options{
			Addr:        "localhost:6379",
			DB:          0,
			MaxRetries:  2,
			IdleTimeout: time.Duration(30) * time.Second,
		})
)

func TestRedisSet(t *testing.T) {
	key := "set_not_exist"
	client.Del(key)
	ms, err := client.SMembers(key).Result()
	log.Printf("test set not exist: ms=%v err=%v", ms, err)
}

func TestHashKey(t *testing.T) {
	key := "test_hash"
	v, err := client.HGet(key, "uid").Int()
	// key不存在，返回redis.nil
	log.Printf("GetNonExistKey: v=%v err=%v", v, err)

	key2 := "test_hash_2"
	client.HSet(key2, "name", 1).Err()
	log.Printf("init: err=%v", v)

	v, err = client.HGet(key2, "uid").Int()
	// key不存在，返回redis.nil
	log.Printf("GetExistKey: v=%v err=%v", v, err)

	vs, err := client.HMGet("dadafdad", "aa", "bb").Result()
	// key不存在，返回redis.nil
	log.Printf("GetExistKey: vs=%v err=%v", vs, err)
}

func TestRedisLish(t *testing.T) {
	ln := "list"
	_, err := client.RPop(ln).Result()
	if err != nil {
		log.Printf("TestRedisLish: ln=%v err=%v", ln, err)
	}

	ln2 := "list2"
	_, err = client.LPush(ln2, "e1").Result()
	log.Printf("TestRedisLish: ln=%v err=%v", ln2, err)

	x, err := client.RPop(ln2).Result()
	log.Printf("TestRedisLish: ln=%v x=%v err=%v", ln2, x, err)
	x, err = client.RPop(ln2).Result()
	log.Printf("TestRedisLish: ln=%v x=%v err=%v", ln2, x, err)

	ss, err := client.LRange("11220", 0, 0).Result()
	log.Printf("TestRedisLRange: ss=%v err=%v", ss, err)
}

func TestRedisZset(t *testing.T) {
	z1 := redis.Z{
		Score:  -1,
		Member: "s1",
	}
	z2 := redis.Z{
		Score:  10,
		Member: "s2",
	}

	client.ZAdd("zsetkey", z1, z2)

	// zset key: key不存在或者member不存在, 返回Redis.Nil
	score, err := client.ZScore("zsetkey", "s3").Result()
	if err != nil {
		log.Printf("zset key: err=%v", err)
	} else {
		log.Printf("zset key: score=%v err=%v", score, err)
	}

	cnt, err := client.ZCard("ttt").Result()
	log.Printf("TestRedisZset: cnt=%v err=%v", cnt, err)

	// zset key: key不存在, ZRange返回[]
	zs, err := client.ZRange("zsetkey", 100, 104).Result()
	log.Printf("TestRedisZset: zrange zs=%v err=%v", zs, err)
}

func TestRedis(t *testing.T) {

	err := client.SAdd("name", "li", "zhang").Err()
	client.SAdd("name", "wang", "zhang").Err()
	if err != nil {
		fmt.Println(err)
	}

	m, err := client.SMembersMap("name").Result()
	if err != nil {
		fmt.Println(err)
	}
	fmt.Println(m)

	err = client.SAdd("emptyset", []string{""}).Err()
	if err != nil {
		fmt.Println(err)
	}
	// set key: key不存在时，err == nil, list为空
	list, err := client.SMembers("emptyset").Result()
	if err != nil {
		fmt.Println(err)
	}
	fmt.Println(list, "len=", len(list))

	// string key: key不存在时，返回Redis.Nil
	s, err := client.Get("sets").Result()
	if err != nil {
		fmt.Println(err)
	}
	fmt.Println(s)

	// zset key: key不存在或者member不存在, 返回Redis.Nil
	rank, err := client.ZRank("zsetkey", "name").Result()
	if err != nil {
		fmt.Println("zset key:", err)
	} else {
		fmt.Println("zset key:", rank, err)
	}

	client.ZAdd("zsetkey", redis.Z{Member: "trump", Score: 10})
	client.ZAdd("zsetkey", redis.Z{Member: "leo", Score: 9})
	rank, err = client.ZRank("zsetkey", "pelosi").Result()
	if err != nil {
		fmt.Println("zset key:", err)
	} else {
		fmt.Println("zset key:", rank, err)
	}

	mems, err := client.ZRevRangeWithScores("zsetkey1", 0, -1).Result()
	fmt.Println("zrevrange:", mems, err)

	v, err := client.Exists("name").Result()
	fmt.Println("exists:", v, err)
	v, err = client.Exists("agename").Result()
	fmt.Println("exists:", v, err)
}

func TestStringKey(t *testing.T) {
	s, err := client.Get("some_key").Result()
	if err != nil {
		log.Printf("TestStringKey: err=%v", err)
		return
	}
	log.Printf("TestStringKey: s=%v", s)
}
