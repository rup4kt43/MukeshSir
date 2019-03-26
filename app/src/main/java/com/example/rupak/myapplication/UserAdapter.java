package com.example.rupak.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context dco_context;
    private ArrayList<UserDto> userDtoArrayList;

    public UserAdapter(Context dco_context, ArrayList<UserDto> userDtos) {
        this.dco_context = dco_context;
        this.userDtoArrayList = userDtos;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(dco_context).inflate(R.layout.user_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
       viewHolder.id.setText(String.valueOf(userDtoArrayList.get(i).getId()));
        viewHolder.v_name.setText(userDtoArrayList.get(i).getName());
        viewHolder.v_address.setText(userDtoArrayList.get(i).getAddress());
    }

    @Override
    public int getItemCount() {
        return userDtoArrayList.size();
    }
//    private final MainActivity context;
//
//    private final List<HashMap<String, String>> user;
//    private final UserInterface callback;
//
//    public UserAdapter(MainActivity mainActivity, UserInterface callback, List<HashMap<String,String>> users) {
//        this.context = mainActivity;
//        this.callback = callback;
//        this.user = users;
//    }
//
//
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View view   = LayoutInflater.from(context).inflate(R.layout.user_list,null,false);
//        return (new UserHolder(view));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
//        HashMap<String,String> userlist = user.get(i);
//        final String id = userlist.get("ID");
//        final String name = userlist.get("NAME");
//        String ad = userlist.get("ADDRESS");
//        uid.setText(id);
//        uname.setText(name);
//        uaddress.setText(ad);
//        uname.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                callback.listItemClicked(name,id);
//            }
//        });
//
//
//
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return user.size();
//    }
//    private TextView uid,uname,uaddress;
//    private class UserHolder extends RecyclerView.ViewHolder {
//        public UserHolder(View view) {
//            super(view);
//            uid = view.findViewById(R.id.tv_id);
//            uname = view.findViewById(R.id.tv_Name);
//            uaddress = view.findViewById(R.id.tv_address);
//
//
//
//
//        }
//    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView id;
        private TextView v_name;
        private TextView v_address;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             id = itemView.findViewById(R.id.tv_id);
            v_name = itemView.findViewById(R.id.tv_Name);
            v_address = itemView.findViewById(R.id.tv_address);
        }
    }
}
