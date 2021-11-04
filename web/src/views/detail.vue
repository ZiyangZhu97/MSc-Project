<template>
  <main role="main">
    <div class="album py-5 bg-light">
      <div class="container">
        <div class="row program-head">
          <div class="col-sm-6" id="cover-video-div">
            <img class="img-fluid" v-bind:src="program.image">
          </div>
          <div class="col-sm-6">
            <h1>{{program.title}}</h1>
            <p class="program-head-item">
              <span><i class="fa fa-clock-o"></i> {{(program.length) | formatSecond}}</span>
              <span>{{PROGRAM_TYPE | optionKV(program.type)}}</span>
              <span><i class="fa fa-user"></i> {{program.favorites}}</span>
            </p>
            <p class="program-head-desc">{{program.summary}}</p>
            <p class="program-head-price">
              <span class="price-now text-danger"><i class="fa fa-gbp"></i>&nbsp;{{program.price}}&nbsp;&nbsp;</span>
            </p>
            <p class="program-head-button-links">
              <a class="btn btn-lg btn-primary btn-shadow" href="javascript:;">like</a>
            </p>
          </div>
        </div>
      </div>
    </div>

  </main>
</template>

<script>

  export default {
    name: 'detail',
    data: function () {
      return {
        id: "",
        program: {},
        chapters: [],
        episodes: [],
        PROGRAM_TYPE: PROGRAM_TYPE
      }
    },
    mounted() {
      let _this = this;
      _this.id = _this.$route.query.id;
      _this.findProgram();
    },
    methods: {
      findProgram() {
        let _this = this;
        _this.$ajax.get(process.env.VUE_APP_SERVER + '/business/web/program/find/' + _this.id).then((response)=>{
          let resp = response.data;
          _this.program = resp.content;
          _this.chapters = _this.program.chapters || [];
          _this.episodes = _this.program.episodes || [];
        })
      },
    }
  }
</script>

<style>
  .program-head {
  }
  .program-head h1 {
    font-size: 2rem;
    margin-bottom: 1.5rem;
  }
  .program-head-item span {
    margin-right: 1rem;
  }
  .program-head-desc {
    font-size: 1rem;
    color: #555
  }

  .program-head a {
  }

  .program-head-price {
    font-size: 2rem;
  }

  @media (max-width: 700px) {
    .program-head h1 {
      font-size: 1.5rem;
    }
  }
</style>
