<template>
  <v-form v-model="valid">
    <v-container style="max-width: 800px; background: #EAF5F1;">
      <v-row class="justify-center">
        <!-- <v-col cols="3" align="right"><div id="thumb">썸네일</div> </v-col> -->
        <v-col cols="12">
          <v-row class="justify-center">
            <v-col cols="4">
              <v-img :src="url" max-height="150" max-width="250" />
            </v-col>
            <v-col cols="6">
              <v-file-input
                @click:clear="url = 'images/lookatme/img.jpg'"
                @change="previewImage"
                v-model="image"
                label="썸네일 업로드"
                prepend-icon="mdi-camera"
              >
              </v-file-input>
              <v-file-input
                v-model="files"
                required
                :rules="[(v) => !!v || '필수 항목입니다']"
                label="영상 업로드"
                show-size
              ></v-file-input>
            </v-col>
          </v-row>
        </v-col>
        <v-col>
          <v-row>
            <v-col cols="2">
              <v-select
                v-model="category"
                :items="kind"
                item-text="codeName"
                item-value="code"
                label="카테고리"
                return-object
                single-line
              ></v-select>
            </v-col>
            <v-col cols="10">
              <v-text-field
                v-model="title"
                required
                label="제목"
                :rules="[(v) => !!v || '필수 항목입니다']"
              ></v-text-field>
            </v-col>
          </v-row>
        </v-col>
        <v-col cols="12">
          <v-row>
            <v-col>
              <v-textarea
                v-model="content"
                required
                label="내용"
                :rules="[(v) => !!v || '필수 항목입니다']"
              ></v-textarea>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-container>
    <v-container style="max-width: 800px; height: 200px;">
      <v-col>
        <v-row class="d-flex justify-content-end">
          <v-col cols="3">
            <v-btn block text x-large class="primary-color text-white rounded-0" @click="regist">
              등록
            </v-btn>
          </v-col>
          <v-col cols="3">
            <v-btn block text x-large class="secondary-color text-white rounded-0" @click="cancel">
              취소
            </v-btn>
          </v-col>
        </v-row>
      </v-col>
    </v-container>
  </v-form>
</template>

<script>
import router from '@/router';
import http from '@/http.js';

export default {
  data() {
    return {
      url: 'images/lookatme/img.jpg',
      image: null,
      files: [],
      valid: false,
      category: {
        codeName: '개발',
        code: '1001',
      },
      title: '',
      content: '',
      kind: [
        {
          codeName: '개발',
          code: '1001',
        },
        {
          codeName: '테크',
          code: '1002',
        },
        {
          codeName: '교육',
          code: '1003',
        },
        {
          codeName: '서비스',
          code: '2001',
        },
        {
          codeName: '영업/마케팅',
          code: '2002',
        },
        {
          codeName: '춤',
          code: '3001',
        },
        {
          codeName: '연주',
          code: '3002',
        },
        {
          codeName: '노래',
          code: '3003',
        },
        {
          codeName: '연기',
          code: '3004',
        },
        {
          codeName: '영상',
          code: '3005',
        },
        {
          codeName: '코미디',
          code: '3006',
        },
        {
          codeName: '사진',
          code: '3007',
        },
        {
          codeName: '메이킹/만들기/손재주',
          code: '3008',
        },
        {
          codeName: '여행',
          code: '3009',
        },
        {
          codeName: '요리',
          code: '3010',
        },
        {
          codeName: '미술',
          code: '3011',
        },
        {
          codeName: '마술',
          code: '3012',
        },
        {
          codeName: '스포츠',
          code: '4001',
        },
        {
          codeName: '무술',
          code: '4002',
        },
        {
          codeName: '게임',
          code: '4003',
        },
      ],
    };
  },
  methods: {
    previewImage() {
      this.url = URL.createObjectURL(this.image);
    },
    regist() {
      var router = this.$router;
      console.log(this.files);
      if (!this.valid) {
        Swal.fire({ 
          icon: 'warning', // Alert 타입 
          title: '필수 항목을 입력해주세요.', // Alert 제목 
          text: '필수 항목을 모두 입력해야 등록이 가능합니다.', // Alert 내용 
          confirmButtonColor: '#439474',
        });
        // alert('필수 항목을 입력해주세요.');
      } else {
        console.log(this.image);
        let lookatme = new FormData();
        let userEmail = localStorage.getItem('user_email');
        lookatme.append('thumbnail', this.image);
        lookatme.append('video', this.files);
        lookatme.append('category', this.category.codeName);
        lookatme.append('title', this.title);
        lookatme.append('content', this.content);
        lookatme.append('userEmail', userEmail);
        http.post('/lookatme/upload', lookatme, {
          headers: { 'Content-Type': 'multipart/form-data' },
        })
          .then(({ data }) => {
            Swal.fire({ 
              icon: 'success', // Alert 타입 
              title: '룩앳미 등록에 성공하였습니다.', // Alert 제목 
              text: '등록한 룩앳미 확인이 가능합니다.', // Alert 내용 
              confirmButtonColor: '#439474',
            });
            // alert('등록 성공!');
            console.log(data)
            router.push({ name: 'lookatme' });
          })
          .catch((err) => console.log(err));
      }
    },
    cancel() {
      Swal.fire({ 
          icon: 'question', // Alert 타입 
          title: '작성을 취소하시겠습니까?', // Alert 제목
          text: '작성된 내용은 저장되지 않습니다.', 
          showCancelButton: true,
          showConfirmButton: true,
          confirmButtonColor: '#439474',
          confirmButtonText: `예`,
          cancelButtonText: `아니오`,
      })
      .then((res) => {
          if(res.isConfirmed) {
              router.push({ name: 'lookatme' });
          }
      })
    },
  },
};
</script>

<style>
#thumb {
  vertical-align: middle;
}
</style>
