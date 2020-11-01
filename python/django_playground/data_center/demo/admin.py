from django.contrib import admin
from django import forms

from .models import Question


class QuestionAddForm(forms.ModelForm):
    multi_text = forms.CharField(max_length=200)

    class Meta:
        model = Question
        fields = ["multi_text", "pub_date"]


class QuestionChangeForm(forms.ModelForm):
    class Meta:
        model = Question
        fields = ["question_text", "pub_date"]


@admin.register(Question)
class QuestionAdmin(admin.ModelAdmin):
    def save_model(self, request, obj, form, change):
        log = "debug request={} change={}".format(request, change)
        print(log)
        # chagne
        if change:
            obj.save()
            return

        # add
        mtexts = request.POST.get("multi_text").split(";")
        print(obj.question_text, obj.pub_date, change)
        for x in mtexts:
            q = Question(question_text=x, pub_date=obj.pub_date)
            q.save()

    def get_form(self, request, obj=None, change=False, **kwargs):
        log = "debug request={} change={} kwargs={}".format(request, change, kwargs)
        print(log)
        if obj:
            kwargs['form'] = QuestionChangeForm
        else:
            kwargs['form'] = QuestionAddForm
        return super().get_form(request, obj, change, **kwargs)
