<template>
  <div>
    <input type = "file" ref="file" v-on:change="uploadFile()" v-bind:id="inputId+'-input'">
  </div>
</template>

<script>
  export default {
    name: 'file',
    props: {
      inputId: {
        default: "file-upload"
      },
      suffixs: {
        default: []
      },
      afterUpload: {
        type: Function,
        default: null
      },
    },
    data: function () {
      return {
      }
    },
    methods: {
      uploadFile () {
        let _this = this;
        let formData = new window.FormData();
        let file = _this.$refs.file.files[0];

        // 判断文件格式
        let suffixs = _this.suffixs;
        let fileName = file.name;
        let suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length).toLowerCase();
        let validateSuffix = false;
        for (let i = 0; i < suffixs.length; i++) {
          if (suffixs[i].toLowerCase() === suffix) {
            validateSuffix = true;
            break;
          }
        }
        if (!validateSuffix) {
          Toast.warning("only support " + suffixs.join(",") + " file");
          $("#" + _this.inputId + "-input").val("");
          return;
        }

        // key："file"必须和后端controller参数名一致
        formData.append('file', file);
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/file/admin/upload', formData).then((response)=>{
          let resp = response.data;
          console.log("上传文件成功：", resp);
          _this.afterUpload(resp);
          $("#" + _this.inputId + "-input").val("");
        });
      },


    }
  }
</script>
