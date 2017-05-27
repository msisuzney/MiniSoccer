package com.msisuzney.minisoccer.DQDApi.model;

import java.util.List;

/**
 * Created by Administrator on 2016/5/10.
 */
public class PersonRanking {

    private Content content;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public static class Content {


        private List<String> header;

        private List<Person> data;

        public List<String> getHeader() {
            return header;
        }

        public void setHeader(List<String> header) {
            this.header = header;
        }

        public List<Person> getData() {
            return data;
        }

        public void setData(List<Person> data) {
            this.data = data;
        }


        public static class Header{
            private String _0;
        }

        public static class Person {
            private String person_id;
            private String person_name;
            private String team_id;
            private String team_name;
            private String count;
            private String person_logo;

            public String getPerson_id() {
                return person_id;
            }

            public void setPerson_id(String person_id) {
                this.person_id = person_id;
            }

            public String getPerson_name() {
                return person_name;
            }

            public void setPerson_name(String person_name) {
                this.person_name = person_name;
            }

            public String getPerson_logo() {
                return person_logo;
            }

            public void setPerson_logo(String person_logo) {
                this.person_logo = person_logo;
            }

            public String getTeam_id() {
                return team_id;
            }

            public void setTeam_id(String team_id) {
                this.team_id = team_id;
            }

            public String getTeam_name() {
                return team_name;
            }

            public void setTeam_name(String team_name) {
                this.team_name = team_name;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }
        }
    }


}
