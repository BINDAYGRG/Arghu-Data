package com.arghudata.test;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.database.Cursor;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.app.Dialog;

import java.util.List;

/**
 * Created by Binaya Gurung on 17/03/18.
 */


public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<ContactsSQLite> contacts;
    private Context context;
    private Cursor mcursor;
    private SQLiteHandler db;

    public Adapter(List<ContactsSQLite> contacts, Context context) {
        this.contacts = contacts;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        //db = new SQLiteHandler(getApplicationContext());

        final ContactsSQLite contactss = contacts.get(position);
        holder.name.setText(contactss.getNamenep());
        holder.email.setText(contactss.getOldaddressnep());

        holder.itemView.setOnClickListener(new View.OnClickListener()  {
            public void onClick(View view) {
                Dialog dialog = new Dialog(view.getContext());
                dialog.setContentView(R.layout.webview_layout);
                dialog.show();
                String content = "<style type=\"text/css\">\n" +
                        "\tbody {\n" +
                        "  text-align: center;\n" +
                        "  margin: 40px 0 0 0;\n" +
                        "  background: #f1f1f1;\n" +
                        "  font-family: \"proxima nova\", \"proxima-nova\";\n" +
                        "}\n" +
                        "\n" +
                        "a:active {\n" +
                        "  border-bottom: 3px solid #07736a;\n" +
                        "  position: relative;\n" +
                        "  top: 0px;\n" +
                        "}\n" +
                        "\n" +
                        "span {\n" +
                        "  font-size: 20px;\n" +
                        "  font-weight: 500;\n" +
                        "  color: #444;\n" +
                        "  text-align: left;\n" +
                        "  padding: 0 0 0 5px;\n" +
                        "  display: inline-block;\n" +
                        "}\n" +
                        "\n" +
                        "i {\n" +
                        "  font-style: normal;\n" +
                        "  font-size: 22px;\n" +
                        "  text-decoration: underline;\n" +
                        "  position: relative;\n" +
                        "  top: -15px;\n" +
                        "  left: -4px;\n" +
                        "  opacity: 0.86;\n" +
                        "}\n" +
                        "div {\n" +
                        "  background: #fff;\n" +
                        "  margin: 0 auto;\n" +
                        "  text-align: left;\n" +
                        "  padding: 20px;\n" +
                        "  box-shadow: 1px 1px 1px rgba(0, 0, 0, 0.05);\n" +
                        "  height: 45px;\n" +
                        "  margin-bottom: 3px;\n" +
                        "  border-radius: 1px;\n" +
                        "}\n" +
                        "div:nth-child(even) {\n" +
                        "  background: #fafafa;\n" +
                        "}\n" +
                        "\n" +
                        "</style>\n" +
                        "\n" +
                        "<i>सम्पुर्ण विवरण </i><div><span>नाम:- <b>" +
                        contactss.getNamenep()+"</b></span></div>" +
                        "<div><span>पुरानो ठेगाना:- <b>" +
                        contactss.getOldaddressnep()+"</b></span></div>" +
                        "<div><span>हालको ठेगाना:- <b>" +
                        contactss.getCurrentaddressnep()+"</b></span></div>"+
                        "<div><span>भेटी:- <b>रु." +
                        contactss.getVetinep()+"("+contactss.getVetieng()+")</b></span></div>"+
                        "<div><span>स्याइस्याइ:- <b>" +
                        contactss.getSyainep()+"("+contactss.getSyaieng()+")</b></span></div>"+
                        "<div><span>कैफियत:- <b>" +
                        contactss.getReference()+"</b></span></div>";


                WebView wv = (WebView) dialog
                        .findViewById(R.id.webView);
                wv.loadDataWithBaseURL("http://google.com", content, "text/html", "UTF-8", "");
               // wv.loadData("<p><center><U><H2>"+contactss.getName()+"("+contactss.getName()+")</H2></U></center></p><p><strong>Director : </strong>"+contactss.getName()+"</p><p><strong>Producer : </strong>"+contactss.getName()+"</p><p><strong>Character : </strong>"+contactss.getName()+"</p><p><strong>Summary : </strong>"+contactss.getName()+"</p><p><strong>Synopsis : </strong>"+contactss.getName()+"</p>\n", "text/html", "UTF-8");
                wv.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);

                        return true;
                    }
                });

            }        });

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,email;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
        }
    }
}
