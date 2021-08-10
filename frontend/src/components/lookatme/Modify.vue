<template>
<div>
  <v-form v-model="valid">
    <v-container>
      <v-row justify="center">
        <v-col cols="4" align="right"><div id="thumb">썸네일</div> </v-col>
        <v-col cols="8">
          <v-row>
            <v-img :src="url" max-height="150" max-width="250" />
            <v-col cols="3">
              <v-file-input
                @click:clear="url = this.$store.getters.fileURL + 'ServerFiles/Thumbnail/' + this.lookatme.thumbnail"
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
            :rules="[(v) => !!v || '필수 항목입니다']"
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
            :rules="[(v) => !!v || '필수 항목입니다']"
            filled
            height="300"
          ></v-text-field>
        </v-col>
      </v-row>
    </v-container>
    <v-container>
      <v-row justify="end">
        <v-col cols="3">
          <v-btn block text x-large class="primary-color text-white rounded-0" @click="modify">
            수정
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
</div>
</template>

<script>
import router from '@/router';
import http from '@/http.js';

export default {
    props: {
        lookatme: {
            type: [],
            default: null,
        }
    },
    mounted() {
        console.log(this.lookatme);
    },
  data() {
    return {
      url: this.$store.getters.fileURL + "ServerFiles/Thumbnail/" + this.lookatme.thumbnail,
      image: null,
      valid: false,
      category: {
        codeName: this.lookatme.category,
      },
      title: this.lookatme.title,
      content: this.lookatme.content,
      kind: [
        {
          codeName: '개발',
          },
        {
          codeName: '테크',
          },
        {
          codeName: '교육',
          },
        {
          codeName: '서비스',
        },
        {
          codeName: '영업/마케팅',
          },
        {
          codeName: '예체능',
          },
        {
          codeName: '연주',
          code: '3002',
        },
        {
          codeName: '노래',
          },
        {
          codeName: '연기',
          },
        {
          codeName: '영상',
          },
        {
          codeName: '코미디',
          },
        {
          codeName: '사진',
          },
        {
          codeName: '메이킹/만들기/손재주',
          },
        {
          codeName: '여행',
          },
        {
          codeName: '요리',
          },
        {
          codeName: '미술',
          },
        {
          codeName: '마술',
          },
        {
          codeName: '스포츠',
          },
        {
          codeName: '무술',
          },
        {
          codeName: '게임',
          },
      ],
    };
  },
  methods: {
    previewImage() {
      this.url = URL.createObjectURL(this.image);
    },
    modify() {
      var router = this.$router;
      if (!this.valid) {
        alert('필수 항목을 입력해주세요.');
      } else {
        console.log(this.image);
        let lookatme = new FormData();
        let userEmail = localStorage.getItem('user_email');
        lookatme.append('thumbnail', this.image);
        lookatme.append('category', this.category.codeName);
        lookatme.append('title', this.title);
        lookatme.append('content', this.content);
        lookatme.append('userEmail', userEmail);
        http.post('/lookatme/upload', lookatme, {
          headers: { 'Content-Type': 'multipart/form-data' },
        })
          .then(({ data }) => {
            alert('등록 성공!');
            console.log(data)
            router.push({ name: 'lookatme' });
          })
          .catch((err) => console.log(err));
      }
    },
    cancle() {
      router.go(-1);
    },
  },
};
</script>

<style>
#thumb {
  vertical-align: middle;
}
</style>
