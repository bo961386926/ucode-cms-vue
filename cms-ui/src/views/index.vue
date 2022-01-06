<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :lg="12" :sm="24" style="padding-left: 20px">
        <h2>Cms后台管理框架</h2>
        <p>快速搭建Web系统脚手架</p>
        <p>
          <b>当前版本:</b> <span>v{{ version }}</span>
        </p>
        <!-- <p>
          <el-tag type="danger">&yen;免费开源</el-tag>
        </p> -->
        <p>
          <el-button icon="el-icon-cloudy" plain size="mini" type="danger"
            >&yen;免费开源
          </el-button>
          <el-button
            icon="el-icon-cloudy"
            plain
            size="mini"
            type="primary"
            @click="goTarget('https://gitee.com/decsa/demo-cms')"
            >访问码云
          </el-button>
          <el-button
            icon="el-icon-s-home"
            plain
            size="mini"
            @click="goTarget('http://www.altitude.xin')"
            >访问主页
          </el-button>
        </p>
      </el-col>

      <el-col :lg="12" :sm="24" style="padding-left: 50px">
        <el-row>
          <el-col :span="12">
            <h2>技术选型</h2>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <h4>后端技术</h4>
            <ul>
              <li>SpringBoot</li>
              <li>Spring Security</li>
              <li>JWT</li>
              <li>MyBatis</li>
              <li>Druid</li>
              <li>Fastjson</li>
              <li>...</li>
            </ul>
          </el-col>
          <el-col :span="6">
            <h4>前端技术</h4>
            <ul>
              <li>Vue</li>
              <li>Vuex</li>
              <li>Element-ui</li>
              <li>Axios</li>
              <li>Sass</li>
              <li>Quill</li>
              <li>...</li>
            </ul>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-divider />
    <el-row :gutter="20">
      <el-col :lg="8" :md="12" :sm="24" :xs="24">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>联系信息</span>
          </div>
          <div class="body">
            <p>
              <i class="el-icon-s-promotion"></i> 官网：
              <el-link href="http://www.altitude.xin" target="_blank">
                http://www.altitude.xin
              </el-link>
            </p>
          </div>
        </el-card>
      </el-col>
      <!--      <el-col :lg="8" :md="12" :sm="24" :xs="24">-->
      <!--        <el-card class="update-log">-->
      <!--          <div slot="header" class="clearfix">-->
      <!--            <span>捐赠支持</span>-->
      <!--          </div>-->
      <!--          <div class="body">-->
      <!--            <img-->
      <!--              alt="donate"-->
      <!--              src="https://oscimg.oschina.net/oscnet/up-d6695f82666e5018f715c41cb7ee60d3b73.png"-->
      <!--              width="100%"-->
      <!--            />-->
      <!--            <span style="display: inline-block; height: 30px; line-height: 30px"-->
      <!--              >你可以请作者喝杯咖啡表示鼓励</span-->
      <!--            >-->
      <!--          </div>-->
      <!--        </el-card>-->
      <!--      </el-col>-->
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "1.1.3",
    };
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    },
  },
};
</script>

<style lang="scss" scoped>
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }

  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }

  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>
