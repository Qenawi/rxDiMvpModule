package users.app.dummyx.qenawi.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import users.app.dummyx.qenawi.R;


/**
 * Created by Andorid-win on 12/5/2017.
 */

@SuppressLint("AppCompatCustomView")
public class typefaceitems extends EditText
{
    public typefaceitems(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init(context,attrs);
    }

    public typefaceitems(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public typefaceitems(Context context) {
        super(context);
        init(context,"normal");
    }

    private void init( Context ctx, AttributeSet attrs)
    {
        String customFont="";
        try {
            TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
            customFont = a.getString(R.styleable.CustomTextView_customFont);
            if (customFont==null)
            {
                customFont="normal";
            }
        }catch ( Exception e)
        {
            customFont="normal";
        }
        switch (customFont)
        {
            case "bold":
                Typeface custom_font = Typeface.createFromAsset(getContext().getAssets(),"font/helveticaneueltarabic-bold.ttf");
                setTypeface(custom_font);
                break;
            default:
                Typeface custom_font2 = Typeface.createFromAsset(getContext().getAssets(),"font/helveticaneueltarabic.ttf");
            //    setTypeface(custom_font2);

        }

     //   setTextIsSelectable(true);

    }
    private void init( Context ctx, String attrs)
    {
        String customFont = attrs;
        switch (customFont)
        {
            case "bold":
                Typeface custom_font = Typeface.createFromAsset(ctx.getAssets(),"font/helveticaneueltarabic-bold.ttf");
                setTypeface(custom_font);
                break;
            default:
                Typeface custom_font2 = Typeface.createFromAsset(ctx.getAssets(),"font/helveticaneueltarabic.ttf");
                setTypeface(custom_font2);
        }
  //      setTextIsSelectable(true);
//

    }
}
