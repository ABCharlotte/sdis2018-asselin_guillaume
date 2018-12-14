#!/usr/bin/env bash
curl localhost:8080/competitions > "test"
$x="{
  "_embedded" : {
    "competitions" : [ {
      "EquipesIds" : [ 1, 2 ],
      "name" : "JIG",
      "classement" : [ ],
      "equipesIds" : [ 1, 2 ],
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/competitions/3"
        },
        "competitions" : {
          "href" : "http://localhost:8080/competitions"
        }
      }
    }, {
      "EquipesIds" : [ 5, 6, 8, 9 ],
      "name" : "RegionNancy",
      "classement" : [ ],
      "equipesIds" : [ 5, 6, 8, 9 ],
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/competitions/10"
        },
        "competitions" : {
          "href" : "http://localhost:8080/competitions"
        }
      }
    }, {
      "EquipesIds" : [ ],
      "name" : "CoupeEst",
      "classement" : [ ],
      "equipesIds" : [ ],
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/competitions/11"
        },
        "competitions" : {
          "href" : "http://localhost:8080/competitions"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/competitions"
    }
  }
}⏎"
diff "test" $s
