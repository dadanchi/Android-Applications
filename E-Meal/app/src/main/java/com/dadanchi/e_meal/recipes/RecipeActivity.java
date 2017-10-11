package com.dadanchi.e_meal.recipes;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.dadanchi.e_meal.R;
import com.dadanchi.e_meal.base.BaseDrawerActivity;
import com.dadanchi.e_meal.repositories.RecipeRepository;

import java.util.HashSet;

import javax.inject.Inject;

public class RecipeActivity extends BaseDrawerActivity {
    @Inject
    RecipeContracts.Presenter mPresenter;
    @Inject
    RecipeRepository mRepository;
    private RecipeView mView;

//    private ImageButton mImageButton;
//    private EditText mDescriptionInput;
//    private EditText mTitleInput;
//    private Button uploadBtn;
//    private Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_recipe);
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        HashSet<String> mProducts = (HashSet<String>) extras.get("products");

        mPresenter = new RecipePresenter();
        mPresenter.setRepository(mRepository);
        mPresenter.setProducts(mProducts);

        mView = RecipeView.create();
        mView.setPresenter(mPresenter);

//        mImageButton = (ImageButton) findViewById(R.id.imageButton);
//        mTitleInput = (EditText) findViewById(R.id.et_add_title);
//        mDescriptionInput = (EditText) findViewById(R.id.eet_add_description);
//        uploadBtn = (Button) findViewById(R.id.upload_recipe_btn);
//
//        uploadBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String title = mTitleInput.getText().toString();
//                String description = mDescriptionInput.getText().toString();
//                mPresenter.addRecipe(title, description, mUri);
//            }
//        });
//
//        mImageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                intent.setType("image/*");
//                startActivityForResult(intent, 1);
//            }
//        });

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_recipes, mView)
                .commit();
    }

    @Override
    protected Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    protected void onResume() {
        mView.setPresenter(mPresenter);
        mPresenter.load();
        super.onResume();
    }

    // create
    //
    //
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 1 && resultCode == RESULT_OK) {
//            mUri = data.getData();
//            mImageButton.setImageURI(mUri);
//
////            CropImage.activity()
////                    .setGuidelines(CropImageView.Guidelines.ON)
////                    .start(this);
//        }
////
////        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
////            CropImage.ActivityResult result = CropImage.getActivityResult(data);
////            if (resultCode == RESULT_OK) {
////                mUri = result.getUri();
////                mImageButton.setImageURI(mUri);
////            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
////                Exception error = result.getError();
////            }
////        }
//    }

}
