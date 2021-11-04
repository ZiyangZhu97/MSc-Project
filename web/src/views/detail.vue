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
              <a class="btn btn-lg btn-primary btn-shadow" href="javascript:;">Subscribe</a>
            </p>
          </div>
        </div>

        <div class="row">

          <!-- season episode -->
          <div class="col-md-9">
            <!-- Nav tabs -->
            <ul class="nav nav-tabs">
              <li class="nav-item">
                <a class="nav-link" href="#chapter" data-toggle="tab">Contents</a>
              </li>
            </ul>

            <br>

            <!-- Tab panes -->
            <div class="tab-content">
              <div class="tab-pane active" id="chapter">
                <div v-for="chapter in chapters" class="chapter">
                  <div class="chapter-chapter">
                    <span class="folded-button">{{chapter.name}}</span>
                  </div>
                  <div>
                    <table class="table table-striped">
                      <tr v-for="(s, j) in chapter.episodes" class="chapter-episode-tr">
                        <td class="col-sm-8 col-xs-12">
                          <div class="episode-title">
                            <i class="fa fa-video-camera d-none d-sm-inline"></i>&nbsp;&nbsp;
                            <span class="d-none d-sm-inline">Episode {{j+1}}&nbsp;&nbsp;</span>
                            {{s.title}}
                            <span v-show="s.charge !== EPISODE_CHARGE.CHARGE.key" class="badge badge-primary hidden-xs">free</span>
                          </div>
                        </td>
                        <td class="col-sm-1 text-right">
                          <span class="badge badge-primary">{{s.time | formatSecond}} s</span>
                        </td>
                      </tr>
                    </table>
                  </div>
                </div>
              </div>
            </div>

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
        PROGRAM_TYPE: PROGRAM_TYPE,
        EPISODE_CHARGE: EPISODE_CHARGE
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

          // 将所有的节放入对应的章中
          Tool.sortAsc(_this.chapters, "id");
          for (let i = 0; i < _this.chapters.length; i++) {
            let c = _this.chapters[i];
            c.episodes = [];
            for (let j = 0; j < _this.episodes.length; j++) {
              let s = _this.episodes[j];
              if (c.id === s.chapterId) {
                c.episodes.push(s);
              }
            }
            Tool.sortAsc(c.episodes, "sort");
          }
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

    /* 章节列表 */
  .chapter {
    padding-bottom: 1.25rem;
  }
  .chapter-chapter {
    font-size: 1.25rem;
    padding: 1.25rem;
    background-color: #23527c;
    color: white;
  }
  .chapter-episode-tr {
    font-size: 1rem;
  }
  .chapter-episode-tr td{
    padding: 1rem 1.25rem;
    vertical-align: middle;
  }
  /*鼠标手势*/
  .chapter-episode-tr td .episode-title{
    color: #555;
  }
  .chapter-episode-tr td .episode-title:hover{
    color: #23527c;
    font-weight: bolder;
    cursor: pointer;
  }
  /*行头小图标*/
  .chapter-episode-tr td .episode-title i{
    color: #2a6496;
  }

</style>
