<template>
  <v-form v-model="valid">
      <v-container>
          <v-row justify="center" >
              <v-col cols="4" align=right><div id="thumb">썸네일</div>
              </v-col>
              <v-col cols="8">
                  <v-row>
                  <v-img :src="url"
                    max-height="150"
                    max-width="250" />
            <v-col cols="3">
                  <v-file-input
                    @click:clear="url='images/lookatme/img.jpg'"
                    @change="previewImage"
                    v-model="image"
                    label="사진 파일"
                    filled
                    prepend-icon="mdi-camera"
                    >
                  </v-file-input>
               </v-col>
                  </v-row>
              </v-col>
          </v-row>
          <v-row justify="center">
              <v-col cols="4" align="right">동영상 파일</v-col>
              <v-col>
                  <v-file-input
                    v-model="files"
                    required
                    :rules="[v => !!v || '필수 항목입니다']"
                    filled
                    label="영상 업로드"
                    show-size
                    ></v-file-input>
              </v-col>
          </v-row>
          <v-row justify="center">
              <v-col cols="4" align="right">카테고리</v-col>
              <v-col>
                  <v-select
                v-model="category"
                :items="kind"
                item-text="codeName"
                item-value="code"
                label="Select"
                filled
                return-object
                single-line
                ></v-select>
              </v-col>
          </v-row>
          <v-row justify="center">
              <v-col cols="4" align="right">제목</v-col>
              <v-col>
                  <v-text-field
                    v-model="title"
                    required
                    :rules="[v => !!v || '필수 항목입니다']"
                    filled
                    ></v-text-field>
              </v-col>
          </v-row>
          <v-row justify="center">
              <v-col cols="4" align="right">내용</v-col>
              <v-col>
                  <v-text-field
                    v-model="content"
                    required
                    :rules="[v => !!v || '필수 항목입니다']"
                    filled
                    height="300"
                    ></v-text-field>
              </v-col>
          </v-row>
      </v-container>
      <v-container>
      <v-row justify="end">
        <v-col cols="3">
          <v-btn block text x-large class="primary-color text-white rounded-0" @click="regist">
            등록
          </v-btn>
        </v-col>
        <v-col cols="3">
          <v-btn block text x-large class="secondary-color text-white rounded-0" @click="cancle">
            취소
          </v-btn>
        </v-col>
      </v-row>
      </v-container>
  </v-form>
</template>

<script>
export default {
    data() {
        return {
            url: "images/lookatme/img.jpg",
            image: null,
            files: [],
            valid: false,
            category: {
                codeName: "개발",
                code: "1001"
            },
            title: "",
            content: "",
            kind: [
                {
                    codeName: "개발",
                    code: "1001",
                },
                {
                    codeName: "테크",
                    code: "1002",
                },
                {
                    codeName: "교육",
                    code: "1003",
                },
                {
                    codeName: "서비스",
                    code: "2001",
                },
                {
                    codeName: "영업/마케팅",
                    code: "2002",
                },
                {
                    codeName: "예체능",
                    code: "3001",
                },
                {
                    codeName: "연주",
                    code: "3002",
                },
                {
                    codeName: "노래",
                    code: "3003",
                },
                {
                    codeName: "연기",
                    code: "3004",
                },
                {
                    codeName: "영상",
                    code: "3005",
                },
                {
                    codeName: "코미디",
                    code: "3006",
                },
                {
                    codeName: "사진",
                    code: "3007",
                },
                {
                    codeName: "메이킹/만들기/손재주",
                    code: "3008",
                },
                {
                    codeName: "여행",
                    code: "3009",
                },
                {
                    codeName: "요리",
                    code: "3010",
                },
                {
                    codeName: "미술",
                    code: "3011",
                },
                {
                    codeName: "마술",
                    code: "3012",
                },
                {
                    codeName: "스포츠",
                    code: "4001",
                },
                {
                    codeName: "무술",
                    code: "4002",
                },
                {
                    codeName: "게임",
                    code: "4003",
                }
            ]
        }
    },
    methods: {
        previewImage() {
            this.url = URL.createObjectURL(this.image)
        },
        regist() {
            if (!this.valid) {
                alert('필수 항목을 입력해주세요.')
            } else {
            let lookatme = new FormData();
            lookatme.append('image', this.image);
            lookatme.append('files', this.files);
            lookatme.append('category', this.category.code);
            lookatme.append('title', this.title);
            lookatme.append('content', this.content);
            
            for (var test of lookatme.entries()) {
                console.log(test[0] + ',' + test[1]);
            }
            }
        },
        cancle() {

        }
    }
}
</script>

<style>
#thumb{
    vertical-align: middle;
}
</style>